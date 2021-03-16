package com.charter.service;

import com.charter.dto.AccountDetails;
import com.charter.response.AccountNumberResponse;
import com.charter.response.EmailAddressResponse;

public interface RVMService {

 EmailAddressResponse getRvmEmailAddress(String accountNumber, String phoneNumber);

 AccountNumberResponse getAccountNumber(String systemId, String phoneNumber);

 AccountDetails updateRvmEmailAddress();

}
