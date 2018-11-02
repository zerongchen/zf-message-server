package com.aotain.message.service;

import com.aotain.common.utils.kafka.ICustomerCallback;
import com.aotain.common.utils.model.msg.JobQueue;
import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobProcesser implements ICustomerCallback {

    private Logger logger = LoggerFactory.getLogger(JobProcesser.class);
    public JobProcesser(){
    }
    
    /**
     * 任务处理函数
     */
    public void callback(int threadnum, int partition, long offset, String message) {
        // TODO Auto-generated method stub
        
        try{
            logger.info("start deal job message! threadnum=" + threadnum + ", message=" + message);
            
            //字符串转对象
            JobQueue jobmsg = JobQueue.parseFrom(message, JobQueue.class);
        	
            int jobType = jobmsg.getJobtype().intValue();

            
        	logger.debug("complete deal job message! threadnum=" + threadnum + ", message=" + message);

        }catch(Exception e){
            logger.error("deal job error! threadnum=" + threadnum + ", message=" + message, e);
            MonitorStatisticsUtils.addEvent(e);
        }
        
    }

}
