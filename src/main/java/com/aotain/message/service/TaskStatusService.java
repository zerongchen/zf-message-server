package com.aotain.message.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aotain.common.utils.model.msg.RedisTaskStatus;
import com.aotain.common.utils.model.push.SendData;
import com.aotain.common.utils.push.PushClient;
import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import com.aotain.common.utils.tools.Tools;
import com.aotain.message.entity.MonitorTaskAlarm;
import com.aotain.message.entity.MonitorTaskDetail;
import com.aotain.message.entity.MonitorTaskInfo;
import com.aotain.message.entity.TaskBean;
import com.aotain.message.util.TaskUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TaskStatusService extends IMsgService {

    private Logger logger = LoggerFactory.getLogger(TaskStatusService.class);
    @Autowired
    private MonitorTaskDetailService monitorTaskDetailService;
    @Autowired
    private MonitorTaskInfoService monitorTaskInfoService;
    @Autowired
    private MonitorTaskAlarmService monitorTaskAlarmService;
    @Autowired
    private PushClient client;
    public void execute(String message, long createTime,String ip) {

        try {
            String datestr = Tools.getDatetimeStr(createTime * 1000);

            RedisTaskStatus status = JSON.parseObject(message,RedisTaskStatus.class);
            JSONObject object = JSONObject.parseObject(status.getContent());
            Long messageNo = object.getLong("messageNo");


            TaskBean bean = TaskUtil.getTaskBean(status);
            insertTaskDetail(status, bean,messageNo);

            insertTaskInfo(status, bean);
            if(bean.getMonitorStatus()==1){
              if(insertAlarmTask(status,bean,datestr)){
                  logger.info("taskId="+status.getTaskId()+" produce alarm");
              }
            }

        } catch (Exception e) {
            logger.error("taskStatusService process message= " + message + "error", e);
            MonitorStatisticsUtils.addEvent(e);
        }
    }

    /**
     * @param status
     * @param bean
     */
    private void insertTaskInfo(RedisTaskStatus status, TaskBean bean) {
        MonitorTaskInfo info = new MonitorTaskInfo();
        info.setTaskParams(TaskUtil.getTaskParams(status.getTaskType(), status.getContent()));
        info.setMonitorParams(bean.getMonitorParams());
        info.setMonitorName(bean.getTaskName());
        String modifyTime = Tools.getDatetimeStr(status.getCreateTime() * 1000);
        info.setModifyTime(modifyTime);
        info.setCreateTime(modifyTime);
        Long monitorTaskId = status.getTopTaskId() == null||status.getTopTaskId().intValue()==0 ? status.getTaskId() : status.getTopTaskId();
        info.setMonitorTaskId(monitorTaskId);
        info.setStatus(bean.getMonitorStatus());
        info.setTaskSubType(bean.getTaskSubType());
        info.setTaskType(bean.getTaskType());

        monitorTaskInfoService.insertMonitorTask(info);
       /* if (status.getTopTaskId() == null || (status.getTopTaskId() != null && status.getTopTaskId().intValue() == 0)) {
            monitorTaskInfoService.insertMonitorTask(info);
        } else {
            monitorTaskInfoService.updateMonitorTask(info);
        }*/
    }
    /**
     * @param status
     * @param bean
     */
    private void insertTaskDetail(RedisTaskStatus status, TaskBean bean,Long messageNo) {
        try {
            MonitorTaskDetail detail = new MonitorTaskDetail();
            Long monitorTaskId = status.getTopTaskId() == null||status.getTopTaskId().intValue()==0 ? status.getTaskId() : status.getTopTaskId();
            detail.setTaskType(bean.getTaskType());
            detail.setTaskSubType(bean.getTaskSubType());
            detail.setTaskParams(TaskUtil.getTaskParams(status.getTaskType(), status.getContent()));
            detail.setTaskName(bean.getTaskName());
            detail.setTaskId(status.getTaskId());
            detail.setMonitorTaskId(monitorTaskId);
            detail.setMonitorParams(bean.getMonitorParams());
            detail.setDataId(messageNo);
            String createTime = Tools.getDatetimeStr(status.getCreateTime() * 1000);
            detail.setCreateTime(createTime);
            String completeTime = Tools.getDatetimeStr(new Date().getTime());
            detail.setCompleteTime(completeTime);
            detail.setStatus(status.getStatus());
            monitorTaskDetailService.insertMonitorTaskDetail(detail);

        } catch (Exception e) {
            logger.error(" service insertTaskDetail error", e);
            MonitorStatisticsUtils.addEvent(e);
        }
    }

    //
    private boolean insertAlarmTask(RedisTaskStatus status, TaskBean bean,String datestr){

        Integer taskType = status.getTaskType();
        String taskAlarmContent = "某策略 下发";
        if(taskType!=null && taskType.intValue() == 4){
            if("用户应用策略信息下发".equals(bean.getTaskName())){
                taskAlarmContent=bean.getTaskName();
            }else if("DPI设备通用信息下发".equals(bean.getTaskName())){
                taskAlarmContent=bean.getTaskName();
            }else if("IP 地址段用户信息下发".equals(bean.getTaskName())){
                taskAlarmContent=bean.getTaskName();
            }else{
                taskAlarmContent  = bean.getTaskName()+"下发";
            }
        }

        Integer detailStatus = status.getStatus();

        if(detailStatus!=null&&detailStatus.intValue()==5){
            taskAlarmContent = taskAlarmContent+" 失败";
        }else if(detailStatus!=null&&detailStatus.intValue()==3){
            taskAlarmContent = taskAlarmContent+" 失败";
        }else{
            taskAlarmContent = taskAlarmContent+" 失败";
        }
/***********************************************************/
        SendData data =new SendData();
        data.setAlarmTime(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
        data.setAlarmMessage(taskAlarmContent);
        data.setAlarmParameter(bean.getMonitorParams());
        client.pushMessage(data);
/***********************************************************/

        Long monitorTaskId = status.getTopTaskId() == null||status.getTopTaskId().intValue()==0 ? status.getTaskId() : status.getTopTaskId();
        // 任务状态为 失败
        MonitorTaskAlarm monitorTaskAlarm = new MonitorTaskAlarm();
        monitorTaskAlarm.setMonitorTaskId(monitorTaskId);
        monitorTaskAlarm.setTaskId(status.getTaskId());
        monitorTaskAlarm.setTaskType(taskType);
        monitorTaskAlarm.setTaskSubtype(bean.getTaskSubType());
        // MONITOR_NAME+TASK_NAME 成功|失败
        monitorTaskAlarm.setAlarmContent(taskAlarmContent);
        monitorTaskAlarm.setAlarmParams(bean.getMonitorParams());
        monitorTaskAlarm.setAlarmTime(datestr);
        monitorTaskAlarm.setDealStatus(0);
        boolean b =  monitorTaskAlarmService.insertMonitorTaskAlarm(monitorTaskAlarm);
        return b;

    }


}
