package com.aotain.message.util;

import com.alibaba.fastjson.JSONObject;
import com.aotain.common.utils.model.msg.RedisTaskStatus;
import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import com.aotain.message.entity.TaskBean;
import org.apache.log4j.Logger;

public class TaskUtil {
    public static final int TASK_STATUS_NOMAL = 0;
    public static final int TASK_STATUS_EXCEPTION = 1;
    public static final int ALARM_STATUS_NODEAL = 0;
    public static final int ALARM_STATUS_DEALED = 1;
    /**
     * 写日志
     */
    private static Logger logger = Logger.getLogger(TaskUtil.class);

    public static TaskBean getTaskBean(RedisTaskStatus status) {
        TaskBean bean = new TaskBean();
        Integer taskType = status.getTaskType();

        try {
            if (taskType.intValue() == 4) {
                JSONObject object = JSONObject.parseObject(status.getContent());
                Integer operationType = object.getInteger("operationType");
                Integer messageType = object.getInteger("messageType");
                Integer probeType = object.getInteger("probeType");
                Long messageNo = object.getLong("messageNo");
                String monitorParams = "messageNo=" + messageNo;
                // 子任务失败
                if (status.getStatus().intValue() == 3 || status.getStatus().intValue() == 5) {
                    bean.setMonitorStatus(TaskUtil.TASK_STATUS_EXCEPTION);
                } else {
                    bean.setMonitorStatus(TaskUtil.TASK_STATUS_NOMAL);
                }
                bean.setTaskSubType(4000);
                bean.setTaskName("undifine name");

                // probeType=0 表示 DPI
                if (probeType!=null&&probeType.intValue() == 0) {
                    if (messageType.intValue() == 196) {
                        bean.setTaskName("DPI设备状态查询请求");
                        bean.setTaskSubType(196);
                        monitorParams += ",type=" + object.getString("type");
                    }else if(messageType.intValue() == 0){
                        bean.setTaskName("通用参数设置");
                        bean.setTaskSubType(0);
                    }else if(messageType.intValue() == 1){
                        bean.setTaskName("流量分析结果上报策略");
                        bean.setTaskSubType(1);
                        monitorParams += ",packetType=" + object.getString("packetType")+",packetSubType="+ object.getString("packetSubType");
                    }else if(messageType.intValue() == 2){
                        bean.setTaskName("Web 类流量管理策略");
                        bean.setTaskSubType(2);
                    }else if(messageType.intValue() == 5){
                        bean.setTaskName("VoIP 类流量管理策略");
                        bean.setTaskSubType(5);
                    }else if(messageType.intValue() == 6){
                        bean.setTaskName("通用流量管理策略");
                        bean.setTaskSubType(6);
                    }else if(messageType.intValue() == 7){
                        bean.setTaskName("通用流量标记策略");
                        bean.setTaskSubType(7);
                    }else if(messageType.intValue() == 8){
                        bean.setTaskName("访问指定应用的用户统计策略");
                        bean.setTaskSubType(8);
                     //   monitorParams += ",appType=" + object.getString("appType")+",appId="+ object.getString("appId");
                    }else if(messageType.intValue() == 9){
                        bean.setTaskName("流量镜像策略");
                        bean.setTaskSubType(9);
                    }else if(messageType.intValue() == 10){
                        bean.setTaskName("应用自定义策略");
                        bean.setTaskSubType(10);
                    }else if(messageType.intValue() == 11){
                        bean.setTaskName("骨干网补充通用参数设置");
                        bean.setTaskSubType(11);
                    }else if(messageType.intValue() == 64){
                        bean.setTaskName("用户组归属分配策略");
                        bean.setTaskSubType(64);
                        monitorParams += ",action=" + object.getString("action");
                    }else if(messageType.intValue() == 65){
                        bean.setTaskName("Web信息推送策略");
                        bean.setTaskSubType(65);
                        monitorParams += ",advId=" + object.getString("advId");
                    }else if(messageType.intValue() == 66){
                        bean.setTaskName("一拖N用户管理策略");
                        bean.setTaskSubType(66);
                    }else if(messageType.intValue() == 67){
                        bean.setTaskName("应用层DDoS异常流量管理策略");
                        bean.setTaskSubType(67);
                    }else if(messageType.intValue() == 68){
                        bean.setTaskName("应用层DDoS保护策略对象绑定");
                        bean.setTaskSubType(68);
                    }else if(messageType.intValue() == 69){
                        bean.setTaskName("应用流量流向策略");
                        bean.setTaskSubType(69);
                    }else if(messageType.intValue() == 70){
                        bean.setTaskName("CP/SP资源服务器分析策略");
                        bean.setTaskSubType(70);
                    }else if(messageType.intValue() == 130){
                        bean.setTaskName("IP 地址段用户信息下发");
                        bean.setTaskSubType(130);
                        monitorParams += ",bindAction=" + object.getString("bindAction");
                    }else if(messageType.intValue() == 133){
                        bean.setTaskName("用户应用策略信息下发");
                        bean.setTaskSubType(133);
                        monitorParams += ",bindAction=" + object.getString("bindAction");
                    }else if(messageType.intValue() == 192){
                        bean.setTaskName("DPI设备通用信息下发");
                        bean.setTaskSubType(192);
                        monitorParams += ",ip=" + object.getString("ip");
                    }else if(messageType.intValue() == 199){
                        bean.setTaskName("协议特征库下发策略");
                        bean.setTaskSubType(199);
                    }else if(messageType.intValue() == 200){
                        bean.setTaskName("Web分类库更新策略");
                        bean.setTaskSubType(200);
                        monitorParams += ",ip=" + object.getString("ip");
                    }else if(messageType.intValue() == 201){
                        bean.setTaskName("应用名称对应表下发策略");
                        bean.setTaskSubType(201);
                        monitorParams += ",ip=" + object.getString("ip");
                    }else if(messageType.intValue() == 202){
                        bean.setTaskName("IP地址库下发策略");
                        bean.setTaskSubType(202);
                    }else if(messageType.intValue() == 203){
                        bean.setTaskName("信息推送触发网站列表定义");
                        bean.setTaskSubType(203);
                        monitorParams += ",triggerHostListId=" + object.getString("triggerHostListId");
                    }else if(messageType.intValue() == 204){
                        bean.setTaskName("信息推送触发网站列表定义");
                        bean.setTaskSubType(204);
                        monitorParams += ",triggerKwListId=" + object.getString("triggerKwListId");
                    }else if(messageType.intValue() == 207){
                        bean.setTaskName("HTTPGET报文清洗策略");
                        bean.setTaskSubType(207);
                        monitorParams += ",ip=" + object.getString("ip");
                    }else if(messageType.intValue() == 207001){
                        bean.setTaskName("HTTPGET报文清洗策略:清洗域名白名单");
                        bean.setTaskSubType(207);
                    }else if(messageType.intValue() == 207002){
                        bean.setTaskName("HTTPGET报文清洗策略:清洗域名黑名单");
                        bean.setTaskSubType(207);
                    }else if(messageType.intValue() == 207003){
                        bean.setTaskName("HTTPGET报文清洗策略:清洗URL黑名单");
                        bean.setTaskSubType(207);
                    }
                }else if(probeType!=null&&probeType.intValue() == 1){
                    if(messageType.intValue() == 192) {
                        bean.setTaskName("DPI设备通用信息下发");
                        bean.setTaskSubType(192);
                        monitorParams += ",ip=" + object.getString("ip");
                    }
                }
                bean.setMonitorParams(monitorParams);
                bean.setTaskType(4);
            }

        } catch (Exception e) {
            logger.error("parse args error,",e);
            MonitorStatisticsUtils.addEvent(e);
        }

        return bean;
    }

    public static String getTaskParams(int tasktype, JSONObject content_json) {
        String taskParams = "";
        if (tasktype == 4) {
            taskParams = content_json.toJSONString();
        } else {
            taskParams="undefine";
        }
        return taskParams;
    }

    public static String getTaskParams(int tasktype, String content) {
        JSONObject object = JSONObject.parseObject(content);
        return  getTaskParams(tasktype,object);
    }


}
