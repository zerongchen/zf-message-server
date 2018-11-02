package com.aotain.message.service;

import com.alibaba.fastjson.JSONObject;
import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import com.aotain.common.utils.tools.Tools;
import com.aotain.message.entity.DpiV1PolicyStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {"createTime":1521099938,"createip":"DPI-01/192.168.5.101","message":"{\"ip\":\"192.17.1.0\",\"messageNo\":60,\"messageType\":64,\"policyIp\":\"\",\"probeType\":0,\"status\":1}","type":2}
 */
@Service
public class DpiPolicyStatusService extends IMsgService{

    private Logger logger = LoggerFactory.getLogger(DpiPolicyStatusService.class);
    @Autowired
    private MonitorTaskInfoService MonitorTaskInfoService;

    @Override
    public void execute(String data, long createTime,String ip) {

        try{
            JSONObject msg = JSONObject.parseObject(data);
            int status = msg.getInteger("status"); //message：0-正在发送、1-发送成功、2-发送失败
         //   if(status > 0){
                DpiV1PolicyStatus dpiV1PolicyStatus = new DpiV1PolicyStatus();
                dpiV1PolicyStatus.setDpiIp(msg.getString("ip"));
                dpiV1PolicyStatus.setMessageNo(msg.getLong("messageNo"));
                dpiV1PolicyStatus.setMessageType(msg.getInteger("messageType"));
                dpiV1PolicyStatus.setCreateTime(Tools.getDatetimeStr(createTime * 1000));
                dpiV1PolicyStatus.setStatus(status); //db表：1-成功、2-失败
                int ret = MonitorTaskInfoService.updatePolicyStatus(dpiV1PolicyStatus);
                if(ret<1){
                    MonitorTaskInfoService.insertPolicyStatus(dpiV1PolicyStatus);
                }
          //  }
        }
        catch(Exception e){
            logger.error("deal message exception.message=" + data,e);
            MonitorStatisticsUtils.addEvent(e);
        }
    }

}
