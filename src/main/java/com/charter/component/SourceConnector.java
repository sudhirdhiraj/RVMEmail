package com.charter.component;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

	public ResponseEntity<String> getAccountDetails(String endPointUrl, Map<String, Object> parameterMap) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(parameterMap, headers);
		ResponseEntity<String> responseEntity = null;
		try {
			SSLUtil.turnOffSslChecking();
			String strStartDate = new Date().toString();
			logger.info("Start call to end point ::{}, at :{}", endPointUrl, strStartDate);
			responseEntity = resClient.exchange(endPointUrl, HttpMethod.POST, entity, String.class);
			String strEndDate = new Date().toString();
			logger.info("End of call to end point ::{}, at ::{} ", endPointUrl, strEndDate);
			if (logger.isDebugEnabled()) {
				logger.debug("Response from end point : :{}", responseEntity);
			}
		} catch (Exception e) {
			logger.debug("Exception while making a http call to url ::{},exception ::{}", endPointUrl, e);

		}

		return responseEntity;
	}

}
