package com.charter.service;

import com.charter.dto.AccountDetails;
import com.charter.dto.AccountNumberResponse;

public interface RVMService {

	AccountDetails getRvmEmailAddress(String accountNumber, String phoneNumber);

	AccountNumberResponse getAccountNumber(String systemId, String phoneNumber);

	AccountDetails updateRvmEmailAddress();

}
