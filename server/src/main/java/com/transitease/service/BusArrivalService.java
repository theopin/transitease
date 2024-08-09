package com.transitease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;


@Service("busArrivalService")
public class BusArrivalService {

	@Autowired
	@Qualifier("transportApiService")
	private TransportApiService transportApiService;

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
