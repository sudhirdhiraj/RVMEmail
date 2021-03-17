package com.charter.response;

import java.util.List;

public class PhoneDetailsResponse {

	private String phoneNumber;
	
	private List<String> emails;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	
	
}
