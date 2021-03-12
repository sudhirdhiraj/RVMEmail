package com.charter.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_DEFAULT)
public class AccountNumberResponse {

	private String tsnId;
	
	private Timestamp timeStamp;
	
	private String accountNumber;
	
	private String divisionId;
	
	private String soloAccountNumber;
	
	private String phoneNumber;
	
	private String responseDescription;

	private String responseCode;

	public String getTsnId() {
		return tsnId;
	}

	public void setTsnId(String tsnId) {
		this.tsnId = tsnId;
	}

	

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public String getSoloAccountNumber() {
		return soloAccountNumber;
	}

	public void setSoloAccountNumber(String soloAccountNumber) {
		this.soloAccountNumber = soloAccountNumber;
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

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	
}
