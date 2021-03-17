package com.charter.service;

import com.charter.request.EmailAddressRequest;
import com.charter.response.AccountNumberResponse;
import com.charter.response.EmailAddressResponse;
import com.charter.response.EmailUpdateResponse;

public interface RVMService {

 EmailAddressResponse getRvmEmailAddress(String accountNumber, String phoneNumber);

 AccountNumberResponse getAccountNumber(String systemId, String phoneNumber);

 EmailUpdateResponse updateRvmEmailAddress(EmailAddressRequest request);

}
