package com.charter.request;

import java.sql.Timestamp;
import java.util.List;

import com.charter.response.PhoneDetailsResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_DEFAULT)
public class EmailAddressRequest {

	private String txnId;

	// private Timestamp timestamp; // string

	private String accountNumber;

	private List<PhoneDetailsRequest> phones;

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<PhoneDetailsRequest> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDetailsRequest> phones) {
		this.phones = phones;
	}

}