package com.charter.response;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailUpdateResponse {

	private String txnId;

	private Timestamp timestamp;

	private String responseDescription;

	@JsonProperty("ResponseCode")
	private String responseCode;

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
