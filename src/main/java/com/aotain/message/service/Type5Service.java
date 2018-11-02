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

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Type5Service extends IMsgService {

    private Logger logger = LoggerFactory.getLogger(Type5Service.class);

    @Autowired
    private MonitorTaskAlarmService monitorTaskAlarmService;
    @Autowired
    private PushClient pushClient;

    public void execute(String message, long createTime,String ip) {

        try {
            String datestr = Tools.getDatetimeStr(createTime * 1000);
            JSONObject object = JSONObject.parseObject(message);
            String content = object.getString("content");
            content =  URLDecoder.decode(content, "UTF-8");
            // 任务状态为 失败
            MonitorTaskAlarm monitorTaskAlarm = new MonitorTaskAlarm();
            monitorTaskAlarm.setMonitorTaskId(0);
            monitorTaskAlarm.setTaskId(0);
            monitorTaskAlarm.setTaskType(5);
            monitorTaskAlarm.setTaskSubtype(5000);
            // MONITOR_NAME+TASK_NAME 成功|失败
            monitorTaskAlarm.setAlarmContent(content);
            monitorTaskAlarm.setAlarmParams("");
            monitorTaskAlarm.setAlarmTime(datestr);
            monitorTaskAlarm.setDealStatus(0);
            boolean b =  monitorTaskAlarmService.insertMonitorTaskAlarm(monitorTaskAlarm);
            logger.debug("type=5,insert into message={"+message+"},status:"+b);
            /***********************************************************/
            try {
                SendData data =new SendData();
                data.setAlarmTime(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
                data.setAlarmMessage(content);
                data.setAlarmParameter("");
                pushClient.pushMessage(data);
                logger.debug("type=5,invoke pushclient success.");
            } catch (Exception e) {
                logger.debug("type=5,invoke pushclient error.",e);
                MonitorStatisticsUtils.addEvent(e);
            }
            /***********************************************************/

        } catch (Exception e) {
            logger.error("taskStatusService process message= " + message + "error", e);
            MonitorStatisticsUtils.addEvent(e);
        }
    }

}
