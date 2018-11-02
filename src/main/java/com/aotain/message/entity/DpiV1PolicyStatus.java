package com.aotain.message.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DpiV1PolicyStatus {

	private int messageType;
	
	private long messageNo;
	
	private String dpiIp;

	private int status;
	
	private String createTime;

	@Override
	public String toString() {
		return "DpiV1PolicyStatus{" +
				"messageType=" + messageType +
				", messageNo=" + messageNo +
				", dpiIp='" + dpiIp + '\'' +
				", status=" + status +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
