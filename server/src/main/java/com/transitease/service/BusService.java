package com.transitease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service("busService")
public class BusService {

	@Autowired
	@Qualifier("dataCacheService")
	private DataCacheService dataCacheService;

	public Object getBusServiceDetails(String serviceNumber) {
		return dataCacheService.getDataByKey(CacheEndpoints.BUS_SERVICES.getEndpoint());
	}

	public Object getBusRouteDetails(String serviceNumber) {
		return dataCacheService.getDataByKey(CacheEndpoints.BUS_ROUTES.getEndpoint());
	}

	public Object getBusStopDetails(String busStopCode) {
		return dataCacheService.getDataByKey(CacheEndpoints.BUS_STOPS.getEndpoint());
	}

}
