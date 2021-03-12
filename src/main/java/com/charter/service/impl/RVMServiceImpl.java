package com.charter.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.charter.component.SourceConnector;
import com.charter.dto.AccountDetails;
import com.charter.dto.AccountNumberResponse;
import com.charter.service.RVMService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Service
public class RVMServiceImpl implements RVMService {

	@Value("${accountdetails.uri}")
	private String emailurl;

	@Value("${sourceurl.accountnumber.uri}")
	private String accountUrl;

	@Autowired
	SourceConnector sourceConnector;

	@Override
	public AccountDetails getRvmEmailAddress(String accountNumber, String phoneNumber) {

		// return sourceConnector.getAccountDetails(emailurl, accountNumber);

		return new AccountDetails();
	}

	@Override
	public AccountNumberResponse getAccountNumber(String systemId, String phoneNumber) {

		AccountNumberResponse acnResp = new AccountNumberResponse();

		Date date = new Date();

		// dynamic josn object - for request
		JSONObject json = new JSONObject();

		json.put("systemID", systemId);
		json.put("phonenumber", phoneNumber);

		JSONObject request = new JSONObject();

		request.put("findDigitalTNRequest", json);

		ResponseEntity<String> resp = null;
		try {
			resp = sourceConnector.getAccountNumberDetails(accountUrl, request.toString());
			if ((resp.getStatusCode() == HttpStatus.OK)) {

				ObjectMapper obj = new ObjectMapper();

				JSONObject response = obj.readValue(resp.getBody(), JSONObject.class);

				acnResp.setAccountNumber(response.getJSONObject("findDigitalTNResponse").getString("accountNumber"));
				acnResp.setDivisionId(response.getJSONObject("findDigitalTNResponse").getString("divisionID"));
				acnResp.setSoloAccountNumber(
						response.getJSONObject("findDigitalTNResponse").getString("soloAccountNumber"));
				acnResp.setPhoneNumber(response.getJSONObject("findDigitalTNResponse").getString("telephoneNumber"));

			} else {
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				acnResp.setTsnId("");
				acnResp.setTimeStamp(timestamp);
				acnResp.setResponseDescription("Failed to retrieve response");
				acnResp.setResponseCode(resp.getStatusCode().toString());
			}

		} catch (Exception e) {
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
