package com.charter.request;

import java.util.List;

public class PhoneDetailsRequest {

	private String phoneNumber;
	
	private List<String> emailAddressList;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<String> getEmailAddressList() {
		return emailAddressList;
	}

	public void setEmailAddressList(List<String> emailAddressList) {
		this.emailAddressList = emailAddressList;
	}
	
}
