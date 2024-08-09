package com.transitease.service;

import com.transitease.dto.bus.arrival.BusServiceArrivalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;


@Service("busArrivalService")
public class BusArrivalService {

	@Autowired
	@Qualifier("transportApiService")
	private TransportApiService transportApiService;

	public BusServiceArrivalDTO getBusArrivalTime(String busStopCode, String serviceNumber) throws ExecutionException, InterruptedException {

		String urlTemplate = "/BusArrivalv2?BusStopCode={0}&ServiceNo={1}";
		String endpointAndParamString = MessageFormat.format(urlTemplate, busStopCode, serviceNumber);

		return (BusServiceArrivalDTO) transportApiService
			.makeGetRequest(endpointAndParamString, BusServiceArrivalDTO.class).get();
	}

	public BusServiceArrivalDTO getArrivalsAtBusStop(String busStopCode)
			throws ExecutionException, InterruptedException {

		String urlTemplate = "/BusArrivalv2?BusStopCode={0}";
		String endpointAndParamString = MessageFormat.format(urlTemplate, busStopCode);

		return (BusServiceArrivalDTO) transportApiService
			.makeGetRequest(endpointAndParamString, BusServiceArrivalDTO.class).get();
	}


}
