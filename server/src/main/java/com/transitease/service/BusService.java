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

	public Object getBusServiceDetails(String serviceNumber) {
		String urlTemplate = "/BusServices?{0}";
		String endpointAndParamString = MessageFormat.format(urlTemplate, serviceNumber);

		return transportApiService.makeGetRequest(endpointAndParamString);
	}

	public Object getBusRouteDetails(String serviceNumber) {
		String urlTemplate = "/BusRoutes?{0}";
		String endpointAndParamString = MessageFormat.format(urlTemplate, serviceNumber);

		return transportApiService.makeGetRequest(endpointAndParamString);
	}

	public Object getBusArrivalTime(String busStopCode, String serviceNumber) {

		String urlTemplate = "/BusArrivalv2?BusStopCode={0}&ServiceNo={1}";
		String endpointAndParamString = MessageFormat.format(urlTemplate, busStopCode, serviceNumber);

		return transportApiService.makeGetRequest(endpointAndParamString);
	}

	public Object getBusStopDetails(String busStopCode) {
		String urlTemplate = "/BusStops?{0}";
		String endpointAndParamString = MessageFormat.format(urlTemplate, busStopCode);

		return transportApiService.makeGetRequest(endpointAndParamString);
	}


	public Object getArrivalsAtBusStop(String busStopCode) {

		String urlTemplate = "/BusArrivalv2?BusStopCode={0}";
		String endpointAndParamString = MessageFormat.format(urlTemplate, busStopCode);

		return transportApiService.makeGetRequest(endpointAndParamString);
	}


}
