package com.aotain.message;


import com.aotain.message.service.TaskStatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-base-message.xml"})
public class test_execute {

    @Autowired
    private TaskStatusService taskStatusService;
    @Test
    public void test1() {
        System.out.println(taskStatusService);
        String message = "{\"content\":{\"messageNo\":2,\"messageType\":196,\"messageSequenceNo\":64,\"probeType\":0,\"operationType\":2,\"frequence\":2,\"type\":2},\"createIp\":\"2016-0607-bdi/192.168.4.243\",\"createTime\":1519633776,\"expireTime\":0,\"interval\":30,\"maxTimes\":3,\"nextTime\":1519633839,\"status\":4,\"taskId\":483,\"taskType\":4,\"times\":2,\"topTaskId\":0}";
        taskStatusService.execute(message,1519633776,"");
    }

    /**
     *
     *  {"createTime":1520384954,"createip":"DPI-01/192.168.5.101","message":"{\"content\":{\"appThresholdDown\":12,\"messageNo\":5,\"messageType\":6,\"appType\":1,\"messageSequenceNo\":12,\"appId\":17,\"appThresholdUp\":12},\"createIp\":\"2017-0423-0erw/192.168.3.163\",\"createTime\":1520384893,\"expireTime\":0,\"interval\":30,\"maxTimes\":3,\"nextTime\":1520384954,\"status\":5,\"taskId\":643,\"taskType\":4,\"times\":2,\"topTaskId\":0}","type":1}
     */
    @Test
    public void test2() {
        System.out.println(taskStatusService);
        String message = "{\"content\":{\"appThresholdDown\":12,\"messageNo\":5,\"messageType\":6,\"appType\":1,\"messageSequenceNo\":12,\"appId\":17,\"appThresholdUp\":12},\"createIp\":\"2017-0423-0erw/192.168.3.163\",\"createTime\":1520384893,\"expireTime\":0,\"interval\":30,\"maxTimes\":3,\"nextTime\":1520384954,\"status\":5,\"taskId\":643,\"taskType\":4,\"times\":2,\"topTaskId\":0}";
        taskStatusService.execute(message,1520384954,"");
    }


}
