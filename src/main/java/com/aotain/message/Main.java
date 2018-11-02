package com.aotain.message;

import com.aotain.common.config.LocalConfig;
import com.aotain.common.utils.kafka.KafkaCustomer;
import com.aotain.common.utils.monitorstatistics.ModuleConstant;
import com.aotain.common.utils.tools.CommonConstant;
import com.aotain.common.utils.tools.MonitorStatisticsUtils;
import com.aotain.message.service.MessageProcesser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;


public class Main {
    /**
     * 写日志
     */
    private static Logger logger = LoggerFactory.getLogger(Main.class);
   
    /**
     * 消费者
     */
    private static KafkaCustomer customer = null;

    private static int msgThreadnum = 1;
    
    /**
     * kafka消费组ID
     */
    private static String KAFKA_QUEUE_GROUP_ID = "zf_message_server";

    /**
     * 启动方法
     * 
     * @param args
     *            输入参数
     */
    public static void main(String[] args) {
    	new ClassPathXmlApplicationContext("classpath*:spring-base-message.xml");
    	MonitorStatisticsUtils.initModuleALL(ModuleConstant.MODULE_MESSAGE_EXEC);

        //获得kafka消费配置
        Map<String, Object> conf = LocalConfig.getInstance().getKafkaCustomerConf();
        conf.put("group.id", KAFKA_QUEUE_GROUP_ID);
        
        //开始消费kafka队列消息
        customer = new KafkaCustomer(conf);
        MessageProcesser msgProcesser = new MessageProcesser();

        //线程数
        msgThreadnum = Integer.parseInt(LocalConfig.getInstance().getHashValueByHashKey("message.customer.threadnum"));
        customer.customer(CommonConstant.KAFKA_QUEUE_NAME_NOTICE, msgThreadnum, msgProcesser);
        

        // 退出程序执行
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {                
                // 停止消费
                customer.shutdown();
                logger.info("zf message server shutdown customer!");
            }
        });

        logger.info("zf message server start complete!");
    }
}
