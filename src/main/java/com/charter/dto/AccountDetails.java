package com.charter.dto;

import java.util.List;

public class AccountDetails {
    private String txnId;

    private String accountNumber;

    private List<PhoneDetails> phones;

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

	public List<PhoneDetails> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneDetails> phones) {
		this.phones = phones;
	}

}
