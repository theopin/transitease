package com.transitease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service("taxiService")
public class TaxiService {

	@Autowired
	@Qualifier("dataCacheService")
	private DataCacheService dataCacheService;

	public Object getListOfTaxiStands() {
		return dataCacheService.getDataByKey(CacheEndpoints.TAXI_STANDS.getEndpoint());
	}

}
