package com.charter.dto;

import java.sql.Timestamp;

public class BaseEntity {

	private String txnId;

	private Timestamp timestamp;

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}