package com.charter.service;

import com.charter.dto.AccountDetails;

public interface RVMService {

	AccountDetails getRvmEmailAddress(String accountNumber, String phoneNumber);

	AccountDetails getAccountNumber(String systemId, String phoneNumber);

	AccountDetails updateRvmEmailAddress();

}
