package com.charter.dto;

import java.time.LocalDateTime;

public class BaseEntity {

	private String tsnId;

	private LocalDateTime timeStamp;

	public String getTsnId() {
		return tsnId;
	}

	public void setTsnId(String tsnId) {
		this.tsnId = tsnId;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
