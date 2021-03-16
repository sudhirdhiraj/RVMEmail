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
import com.charter.dto.AccountDetails;
import com.charter.dto.Resp;
import com.charter.response.AccountNumberResponse;
import com.charter.response.EmailAddressResponse;
import com.charter.response.PhoneDetailsResponse;
import com.charter.service.RVMService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RVMServiceImpl implements RVMService {

	//https://domain.portals.dev-spectrum.net/preferences/v1/readablevoicemail/
	@Value("${accountdetails.uri}")
	private String emailurl;

	@Value("${sourceurl.accountnumber.uri}")
	private String accountUrl;

	@Autowired
	SourceConnector sourceConnector;

	@Override
	public EmailAddressResponse getRvmEmailAddress(String accountNumber, String phoneNumber) {
		
		EmailAddressResponse response = new EmailAddressResponse();
		ResponseEntity<String> sourceResponse = null;
		try {
			sourceResponse = sourceConnector.getEmailAddressDetails(emailurl+accountNumber);
			if (sourceResponse.getStatusCode() == HttpStatus.OK) {
				
				preapreEmailAddressResponse(accountNumber, phoneNumber, response, sourceResponse);
			} else {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				response.setTxnId("");
				response.setTimestamp(timestamp);
				response.setResponseDescription("Failed to retrieve response");
				response.setResponseCode(sourceResponse.getStatusCode().toString());
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			response.setTxnId("");
			response.setTimestamp(timestamp);
			response.setResponseDescription("Failed to retrieve response/ internal server error");
			response.setResponseCode(sourceResponse.getStatusCode().toString());
		}
		return response;
	}

	/**
	 * @param accountNumber
	 * @param phoneNumber
	 * @param response
	 * @param sourceResponse
	 */
	private void preapreEmailAddressResponse(String accountNumber, String phoneNumber, EmailAddressResponse response, ResponseEntity<String> sourceResponse) {
		
		JSONObject json = new JSONObject(sourceResponse.getBody());
		JSONArray phones = json.getJSONArray("phones"); 
		response.setTxnId(json.getString("txnId"));
		response.setTimestamp(new Timestamp(System.currentTimeMillis()));
		for (int i=0; i< phones.length()+1; i++) {
			JSONObject phone = new JSONObject(phones.get(i));
			if(phoneNumber.equals(phone.getString("phoneNumber"))) {
				JSONArray emails = phone.getJSONArray("emails");
				response.setAccountNumber(accountNumber);
				PhoneDetailsResponse phoneDetails = new PhoneDetailsResponse();
				phoneDetails.setPhoneNumber(phoneNumber);
				List<String> emailList = new ArrayList<>();
				emails.toList().forEach(e -> emailList.add(e.toString()));
				phoneDetails.setEmailAddressList(emailList);
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
				acnResp.setTsnId("4532We");
				acnResp.setTimeStamp(timestamp);
				acnResp.setAccountNumber(response.getFindDigitalTNResponse().getAccountNumber());
				acnResp.setDivisionId(response.getFindDigitalTNResponse().getDivisionID());
				acnResp.setSoloAccountNumber(response.getFindDigitalTNResponse().getSoloAccountNumber());
				acnResp.setPhoneNumber(response.getFindDigitalTNResponse().getTelephoneNumber());

			} else {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				acnResp.setTsnId("");
				acnResp.setTimeStamp(timestamp);
				acnResp.setResponseDescription("Failed to retrieve response");
				acnResp.setResponseCode(resp.getStatusCode().toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Response Status:"+resp.getStatusCode());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			acnResp.setTsnId("");
			acnResp.setTimeStamp(timestamp);
			acnResp.setResponseDescription("Failed to retrieve response/ internal server error");
			acnResp.setResponseCode(resp.getStatusCode().toString());
		}

		return acnResp;
	}

	@Override
	public AccountDetails updateRvmEmailAddress() {

		return null;
	}

}