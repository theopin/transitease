package com.transitease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service("busService")
public class BusService {

	@Autowired
	@Qualifier("transportApiService")
	private TransportApiService transportApiService;

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


	public Object getBusArrivalTime(String busStopCode, String serviceNumber) {

		String urlTemplate = "/BusArrivalv2?BusStopCode={0}&ServiceNo={1}";
		String endpointAndParamString = MessageFormat.format(urlTemplate, busStopCode, serviceNumber);

		return transportApiService.makeGetRequest(endpointAndParamString, Object.class);
	}

	public Object getArrivalsAtBusStop(String busStopCode) {

		String urlTemplate = "/BusArrivalv2?BusStopCode={0}";
		String endpointAndParamString = MessageFormat.format(urlTemplate, busStopCode);

		return transportApiService.makeGetRequest(endpointAndParamString, Object.class);
	}


}
