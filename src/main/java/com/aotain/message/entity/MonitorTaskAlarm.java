package com.aotain.message.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MonitorTaskAlarm {

    // 序列为 IDC_MONITOR_TASK_AlARM_ID
    private long alarmId;
    private long monitorTaskId;
    private long taskId;
    private int taskType;
    private int taskSubtype;
    private String alarmContent;
    private String alarmParams;
    private String alarmTime;
    private int dealStatus;
    private String dealSolution;
    private String dealUser;
    private String dealTime;

    @Override
    public String toString() {
        return "MonitorTaskAlarm{" +
                "alarmId=" + alarmId +
                ", monitorTaskId=" + monitorTaskId +
                ", taskId=" + taskId +
                ", taskType=" + taskType +
                ", taskSubtype=" + taskSubtype +
                ", alarmContent='" + alarmContent + '\'' +
                ", alarmParams='" + alarmParams + '\'' +
                ", alarmTime='" + alarmTime + '\'' +
                ", dealStatus=" + dealStatus +
                ", dealSolution='" + dealSolution + '\'' +
                ", dealUser='" + dealUser + '\'' +
                ", dealTime='" + dealTime + '\'' +
                '}';
    }
}
