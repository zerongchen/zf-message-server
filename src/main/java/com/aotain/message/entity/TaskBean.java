package com.aotain.message.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskBean {

	private String taskName;
	
	private int taskType;
	
	private int taskSubType;
	
	private String monitorParams;
	
	private int monitorStatus = 0;

	@Override
	public String toString() {
		return "TaskBean [taskName=" + taskName + ", taskType=" + taskType
				+ ", taskSubType=" + taskSubType + ", monitorParams="
				+ monitorParams + ", monitorStatus=" + monitorStatus + "]";
	}
	
	public boolean isMatched(){
		if(taskName == null || taskName.length() ==0) return false;
		if(taskType == 0) return false;
		if(taskSubType == 0) return false;
		return true;
	}

}
