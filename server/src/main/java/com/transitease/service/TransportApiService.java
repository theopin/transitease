package com.transitease.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("transportApiService")
public class TransportApiService {

	@Value("${transport.api.url}")
	private String transportApiUrl;

	@Value("${transport.api.key}")
	private String transportApiKey;

	@Autowired
	@Qualifier("restTemplateBean")
	private RestTemplate restTemplateObject;

	private static final Logger LOGGER = LogManager.getLogger(TransportApiService.class);

	public Object makeGetRequest(String endpointAndParamString) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("AccountKey", transportApiKey);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		LOGGER.info("url making request to: " + transportApiUrl + endpointAndParamString);


		ResponseEntity<Object> response = restTemplateObject.exchange(
			transportApiUrl + endpointAndParamString,
			HttpMethod.GET,
			entity,
			Object.class
		);

		ObjectMapper mapper = new ObjectMapper();
		try {
			LOGGER.info(mapper.writeValueAsString(response));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		LOGGER.info(response);


		return response.getBody();
	}

}
