package com.charter.service.impl;

import org.springframework.stereotype.Service;

import com.charter.dto.AccountDetails;
import com.charter.service.RVMService;

@Service
public class RVMServiceImpl implements RVMService {

	@Override
	public AccountDetails getRvmEmailAddress(String accountNumber, String phoneNumber) {
		
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
