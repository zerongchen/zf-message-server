package com.aotain.message.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitorTaskInfo {

    /**
     * 监控项任务ID
     */
    private Long monitorTaskId;

    /**
     * 监控项名称
     */
    private String monitorName;
    /**
     * 监控项参数
     */
    private String monitorParams;
    /**
     * 任务类型
     */
    private Integer taskType;
    /**
     * 任务子类型
     */
    private Integer taskSubType;
    /**
     * 任务参数
     */
    private String taskParams;
    /**
     * 处理状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 最后更新时间
     */
    private String modifyTime;

    @Override
    public String toString() {
        return "MonitorTaskInfo{" +
                "monitorTaskId=" + monitorTaskId +
                ", monitorName='" + monitorName + '\'' +
                ", monitorParams='" + monitorParams + '\'' +
                ", taskType=" + taskType +
                ", taskSubType=" + taskSubType +
                ", taskParams='" + taskParams + '\'' +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                '}';
    }
}
