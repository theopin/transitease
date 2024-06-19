package com.transitease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service("taxiService")
public class TaxiService {

	@Autowired
	@Qualifier("transportApiService")
	private TransportApiService transportApiService;

	public Object getListOfTaxiStands() {
		String urlTemplate = "/TaxiStands";

		return transportApiService.makeGetRequest(urlTemplate);
	}

}
