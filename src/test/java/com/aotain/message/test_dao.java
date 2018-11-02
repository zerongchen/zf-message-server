package com.aotain.message;

import com.aotain.message.entity.MonitorTaskAlarm;
import com.aotain.message.entity.MonitorTaskDetail;
import com.aotain.message.entity.MonitorTaskInfo;
import com.aotain.message.service.MonitorTaskAlarmService;
import com.aotain.message.service.MonitorTaskDetailService;
import com.aotain.message.service.MonitorTaskInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-base-message.xml" })
public class test_dao {

    @Autowired
    private MonitorTaskInfoService monitorTaskInfoService;

    @Autowired
    private MonitorTaskDetailService monitorTaskDetailService;

    @Autowired
    private MonitorTaskAlarmService monitorTaskAlarmService;

    @Test
    public void test1() {
        System.out.println(monitorTaskInfoService);
        System.out.println(monitorTaskDetailService);
    }


    @Test
    public void insertTaskInfo() {
        MonitorTaskInfo info = new MonitorTaskInfo();
        info.setCreateTime("1519633817");
        info.setModifyTime("1519633817");
        info.setMonitorName("EU设备状态查询请求");
        info.setMonitorParams("messageType=196");
        info.setMonitorTaskId(1234567L);
        info.setStatus(4);
        info.setTaskParams("");
        info.setTaskSubType(4001);
        info.setTaskType(4);
        monitorTaskInfoService.insertMonitorTask(info);
    }
    @Test
    public void updateTaskInfo() {
        MonitorTaskInfo info = new MonitorTaskInfo();
        info.setCreateTime("2018-02-27 11:13");
        info.setModifyTime("2018-02-28 11:14");
        info.setMonitorName("EU设备状态查询请求");
        info.setMonitorParams("messageType=196");
        info.setMonitorTaskId(123456L);
        info.setStatus(3);
        info.setTaskParams("");
        info.setTaskSubType(4001);
        info.setTaskType(4);
        monitorTaskInfoService.updateMonitorTask(info);
    }

    @Test
    public void insertTaskDetail() {
        MonitorTaskDetail detail = new MonitorTaskDetail();
        detail.setCompleteTime("2018-02-27 12:00:00");
        detail.setCreateTime("2018-02-27 12:00:00");
        detail.setDataId(123L);
        detail.setMonitorParams("messageType=196");
        detail.setMonitorTaskId(123456L);
        detail.setStatus(4);
        detail.setTaskId(123L);
        detail.setTaskName("EU设备状态查询请求");
        detail.setTaskParams("messageType=196");
        detail.setTaskSubType(4);
        detail.setTaskType(2);
        monitorTaskDetailService.insertMonitorTaskDetail(detail);
    }

    @Test
    public void insertTaskAlarm(){
        Long taskId = 123456L;
        int tasktype = 4 ;
        int tasksubtype = 133;
        String taskAlarmContent = "某策略下发 失败";
        String monitorParams = "ip=192.168.12.12";
        String datestr="2018-03-15";
        MonitorTaskAlarm monitorTaskAlarm = new MonitorTaskAlarm();

    //    monitorTaskAlarm.setAlarmId(0);
        monitorTaskAlarm.setMonitorTaskId(taskId);
        monitorTaskAlarm.setTaskId(taskId);
        monitorTaskAlarm.setTaskType(tasktype);
        monitorTaskAlarm.setTaskSubtype(tasksubtype);
        // MONITOR_NAME+TASK_NAME 成功|失败
        monitorTaskAlarm.setAlarmContent(taskAlarmContent);
        monitorTaskAlarm.setAlarmParams(monitorParams);
        monitorTaskAlarm.setAlarmTime(datestr);
        monitorTaskAlarm.setDealStatus(0);
        monitorTaskAlarmService.insertMonitorTaskAlarm(monitorTaskAlarm);

    }
}