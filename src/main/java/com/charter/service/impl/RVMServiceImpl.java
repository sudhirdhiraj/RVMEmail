package com.charter.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.charter.component.SourceConnector;
import com.charter.dto.Resp;
import com.charter.request.EmailAddressRequest;
import com.charter.response.AccountNumberResponse;
import com.charter.response.EmailAddressResponse;
import com.charter.response.EmailUpdateResponse;
import com.charter.response.PhoneDetailsResponse;
import com.charter.service.RVMService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RVMServiceImpl implements RVMService {

	// https://domain.portals.dev-spectrum.net/preferences/v1/readablevoicemail/
	@Value("${accountdetails.uri}")
	private String emailurl;

	@Value("${sourceurl.accountnumber.uri}")
	private String accountUrl;

	@Autowired
	SourceConnector sourceConnector;

	//uc1
	@Override
	public EmailAddressResponse getRvmEmailAddress(String accountNumber, String phoneNumber) {

		EmailAddressResponse response = new EmailAddressResponse();
		ResponseEntity<String> sourceResponse = null;
		try {
			/*
			 * sourceResponse = sourceConnector.getEmailAddressDetails(emailurl +
			 * accountNumber); if (sourceResponse.getStatusCode() == HttpStatus.OK) {
			 * 
			 * preapreEmailAddressResponse(accountNumber, phoneNumber, response,
			 * sourceResponse); } else { Timestamp timestamp = new
			 * Timestamp(System.currentTimeMillis()); response.setTxnId("");
			 * response.setTimestamp(timestamp);
			 * response.setResponseDescription("Failed to retrieve response");
			 * response.setResponseCode(sourceResponse.getStatusCode().toString()); }
			 */
			//  mock response
			 Timestamp timestamp = new Timestamp(System.currentTimeMillis()); response.setTxnId("");
			 response.setTimestamp(timestamp);
			 response.setResponseDescription("Success");
			 response.setResponseCode("200");
			 response.setAccountNumber(accountNumber);
			 PhoneDetailsResponse phones = new PhoneDetailsResponse();
			 phones.setPhoneNumber(phoneNumber);
			 phones.setEmails(Arrays.asList("sdt@gg.com","wers@rt.com"));
			 response.setPhones(Arrays.asList(phones));

		} catch (Exception e) {

			e.printStackTrace();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			response.setTimestamp(timestamp);
			response.setResponseDescription("Failed to retrieve response/ internal server error");
			response.setResponseCode("500");
		}
		return response;
	}

	/**
	 * @param accountNumber
	 * @param phoneNumber
	 * @param response
	 * @param sourceResponse
	 */
	private void preapreEmailAddressResponse(String accountNumber, String phoneNumber, EmailAddressResponse response,
			ResponseEntity<String> sourceResponse) {

		JSONObject json = new JSONObject(sourceResponse.getBody());
		JSONArray phones = json.getJSONArray("phones");
		response.setTxnId(json.getString("txnId"));
		response.setTimestamp(new Timestamp(System.currentTimeMillis()));
		for (int i = 0; i < phones.length() + 1; i++) {
			JSONObject phone = new JSONObject(phones.get(i));
			if (phoneNumber.equals(phone.getString("phoneNumber"))) {
				JSONArray emails = phone.getJSONArray("emails");
				response.setAccountNumber(accountNumber);
				PhoneDetailsResponse phoneDetails = new PhoneDetailsResponse();
				phoneDetails.setPhoneNumber(phoneNumber);
				List<String> emailList = new ArrayList<>();
				emails.toList().forEach(e -> emailList.add(e.toString()));
				phoneDetails.setEmails(emailList);
				response.setPhones(Arrays.asList(phoneDetails));
				break;
			}
		}
	}

	@Override
	public AccountNumberResponse getAccountNumber(String systemId, String phoneNumber) {

		AccountNumberResponse acnResp = new AccountNumberResponse();

		// dynamic json object - for request
		JSONObject json = new JSONObject();

		json.put("systemID", systemId);
		json.put("telephoneNumber", phoneNumber);

		JSONObject request = new JSONObject();

		request.put("findDigitalTNRequest", json);

		ResponseEntity<String> resp = null;
		try {
			resp = sourceConnector.getAccountNumberDetails(accountUrl, request.toString());

			if ((resp.getStatusCode() == HttpStatus.OK)) {

				ObjectMapper obj = new ObjectMapper();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());

				Resp response = obj.readValue(resp.getBody(), Resp.class);
				acnResp.setTxnId("4532We");
				acnResp.setTimestamp(timestamp);
				acnResp.setAccountNumber(response.getFindDigitalTNResponse().getAccountNumber());
				acnResp.setDivisionId(response.getFindDigitalTNResponse().getDivisionID());
				acnResp.setSoloAccountNumber(response.getFindDigitalTNResponse().getSoloAccountNumber());
				acnResp.setPhoneNumber(response.getFindDigitalTNResponse().getTelephoneNumber());

			} else {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				acnResp.setTxnId("");
				acnResp.setTimestamp(timestamp);
				acnResp.setResponseDescription("Failed to retrieve response");
				acnResp.setResponseCode(resp.getStatusCode().toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Response Status:"+resp.getStatusCode());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			acnResp.setTimestamp(timestamp);
			acnResp.setResponseDescription("Failed to retrieve response/ internal server error");
			acnResp.setResponseCode("500");
		}

		return acnResp;
	}

	@Override
	public EmailUpdateResponse updateRvmEmailAddress(EmailAddressRequest requestBody) {

		// Call GET service (UC1) to get the latest emails for all the phone numbers on
		// that account (Step 1)
		EmailAddressResponse emailIdAddressResponse = getRvmEmailAddress(requestBody.getAccountNumber(),
				requestBody.getPhones().get(0).getPhoneNumber());

		EmailUpdateResponse emailUpdateResp = new EmailUpdateResponse();

		ResponseEntity<String> resp = null;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			//JSONObject jsonObj = new JSONObject(emailIdAddressResponse.getPhones().toString());
			/*
			 * resp = sourceConnector.updateEmailAddress(accountUrl, jsonObj.toString()); // jsonObj.toString() -->emailIdAddressResponse.getPhones().toString()
			 * JSONObject jsonRespone = new JSONObject(resp.getBody());
			 */

			emailUpdateResp.setResponseCode("200"); // jsonRespone.getString("ResponseCode")
			emailUpdateResp.setResponseDescription("Success"); // jsonRespone.getString("responseDescription")
			emailUpdateResp.setTimestamp(timestamp); // jsonRespone.getString("timestamp")
			emailUpdateResp.setTxnId("wert5");

		} catch (Exception e) {
			e.printStackTrace();
			emailUpdateResp.setResponseCode("500");
			emailUpdateResp.setResponseDescription("Internal Server Error");
			emailUpdateResp.setTimestamp(timestamp);
		}

		return emailUpdateResp;
	}

}