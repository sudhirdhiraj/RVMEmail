package com.charter.component;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.charter.util.SSLUtil;

@Component
public class SourceConnector {

	private static final Logger logger = LoggerFactory.getLogger(SourceConnector.class);

	@Autowired
	private RestTemplate resClient;

	//uc2
	public ResponseEntity<String> getAccountNumberDetails(String endPointUrl, String request)
			throws KeyManagementException, NoSuchAlgorithmException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
		ResponseEntity<String> responseEntity = null;
		// try {
		SSLUtil.turnOffSslChecking();
		String strStartDate = new Date().toString();
		logger.info("Start call to end point ::{}, at :{}", endPointUrl, strStartDate);
		// responseEntity = resClient.exchange(endPointUrl, String.class,
		// accountNumber);
		responseEntity = resClient.exchange(endPointUrl, HttpMethod.POST, httpEntity, String.class);
		String strEndDate = new Date().toString();
		logger.info("End of call to end point ::{}, at ::{} ", endPointUrl, strEndDate);
		if (logger.isDebugEnabled()) {
			logger.debug("Response from end point : :{}", responseEntity);
		}
		return responseEntity;
	}
	
	//uc1
	public ResponseEntity<String> getEmailAddressDetails(String endPointUrl) throws KeyManagementException, NoSuchAlgorithmException {
		
		SSLUtil.turnOffSslChecking();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> responseEntity = null;
		String strStartDate = new Date().toString();
		logger.info("Start call to end point ::{}, at :{}", endPointUrl, strStartDate);
		responseEntity = resClient.exchange(endPointUrl, HttpMethod.GET, httpEntity, String.class);
		String strEndDate = new Date().toString();
		logger.info("End of call to end point ::{}, at ::{} ", endPointUrl, strEndDate);
		if (logger.isDebugEnabled()) {
			logger.debug("Response from end point : :{}", responseEntity);
		}
		return responseEntity;
	}
	
	//uc3
	public ResponseEntity<String> updateEmailAddress(String endPointUrl, String request)
			throws KeyManagementException, NoSuchAlgorithmException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> httpEntity = new HttpEntity<>(request, headers);
		ResponseEntity<String> responseEntity = null;
		// try {
		SSLUtil.turnOffSslChecking();
		String strStartDate = new Date().toString();
		logger.info("Start call to end point ::{}, at :{}", endPointUrl, strStartDate);
		// responseEntity = resClient.exchange(endPointUrl, String.class,
		// accountNumber);
		responseEntity = resClient.exchange(endPointUrl, HttpMethod.PUT, httpEntity, String.class);
		String strEndDate = new Date().toString();
		logger.info("End of call to end point ::{}, at ::{} ", endPointUrl, strEndDate);
		if (logger.isDebugEnabled()) {
			logger.debug("Response from end point : :{}", responseEntity);
		}
		return responseEntity;
	}

}