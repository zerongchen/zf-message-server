package com.aotain.message.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonitorTaskDetail {

	/**
	 * 子任务ID
	 */
	private Long taskId;
	/**
	 * 监控项任务ID
	 */
	private Long monitorTaskId;
	/**
	 * 子任务名称
	 */
	private String taskName;
	/**
	 * 监控项参数
	 */
	private String monitorParams;
	/**
	 * 任务参数
	 */
	private String taskParams;

	/**
	 * 任务对应表主键
	 */
	private Long dataId;
	/**
	 * 任务类型
	 */
	private int taskType;
	/**
	 * 任务子类型
	 */
	private int taskSubType;
	/**
	 * 处理状态
	 */
	private int status;
	/**
	 * 处理时间
	 */
	private String createTime;

	/**
	 * 任务完成时间
	 */
	private String completeTime;

	@Override
	public String toString() {
		return "MonitorTaskDetail{" +
				"taskId=" + taskId +
				", monitorTaskId=" + monitorTaskId +
				", taskName='" + taskName + '\'' +
				", monitorParams='" + monitorParams + '\'' +
				", taskParams='" + taskParams + '\'' +
				", dataId=" + dataId +
				", taskType=" + taskType +
				", taskSubType=" + taskSubType +
				", status=" + status +
				", createTime='" + createTime + '\'' +
				", completeTime='" + completeTime + '\'' +
				'}';
	}
}
