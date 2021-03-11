package com.charter.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.charter.component.SourceConnector;
import com.charter.dto.AccountDetails;
import com.charter.service.RVMService;

@Service
public class RVMServiceImpl implements RVMService {

	@Value("${accountdetails.uri}")
	private String accounturl;
	
	@Value("${email.uri}")
	private String emailUrl;
	
	@Autowired
	SourceConnector sourceConnector;
	
	@Override
	public AccountDetails getRvmEmailAddress(String accountNumber, String phoneNumber) {
		
		Map<String, Object> param = null;
		
		sourceConnector.getAccountDetails(accounturl, param);
		
		return new AccountDetails();
	}

	@Override
	public AccountDetails getAccountNumber(String systemId, String phoneNumber) {
		
		return null;
	}

	@Override
	public AccountDetails updateRvmEmailAddress() {
		
		return null;
	}

}
