package com.aotain.message.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aotain.common.config.ContextUtil;
import com.aotain.common.utils.kafka.ICustomerCallback;
import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProcesser implements ICustomerCallback {
	
    /**
     * 写日志
     */
    private Logger logger = LoggerFactory.getLogger(MessageProcesser.class);
    
    private IMsgService taskStatusService;

    private IMsgService dpiPolicyStatusService;

    private IMsgService monitorMessageService;

    private IMsgService type5Service;

	public MessageProcesser(){
		this.taskStatusService = ContextUtil.getContext().getBean("taskStatusService",IMsgService.class);
	    this.dpiPolicyStatusService = ContextUtil.getContext().getBean("dpiPolicyStatusService",IMsgService.class);
	    this.monitorMessageService = ContextUtil.getContext().getBean("monitorMessageService",IMsgService.class);
	    this.type5Service = ContextUtil.getContext().getBean("type5Service",IMsgService.class);
	}

    /**
     * 任务处理函数
     */
    public void callback(int threadnum, int partition, long offset, String message) {

        try{
            logger.info("start deal message! threadnum=" + threadnum + ", message=" + message);
            
            JSONObject msg = JSON.parseObject(message);
            //类型,1=任务状态消息,2=EU策略发送状态,3=EU监测过滤日志记录数,4=导出任务记录数
            int type = msg.getInteger("type");
            //消息内容，json字符串
            String data = msg.getString("message");
            //创建时间， UTC时间戳，精确到秒
            long createtime = msg.getLong("createtime");

            //添加消息的IP地址
            String createip = msg.getString("createip");

            if(type == 1){
            	taskStatusService.execute(data,createtime,createip);
            }else if(type == 2){
                dpiPolicyStatusService.execute(data,createtime,createip);
            }else if(type == 4){
                monitorMessageService.execute(data,createtime,createip);
            }else if(type == 5){
                type5Service.execute(data,createtime,createip);
            }

        }catch(Exception e){
            logger.error("deal message error! threadnum=" + threadnum + ", message=" + message, e);
            MonitorStatisticsUtils.addEvent(e);
        }
    }

}
