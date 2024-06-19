package com.transitease.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("trainService")
public class TrainService {

	@Autowired
	@Qualifier("transportApiService")
	private TransportApiService transportApiService;

	public Object getTrainServiceAlerts() {
		String urlTemplate = "/TrainServiceAlerts";

		return transportApiService.makeGetRequest(urlTemplate);
	}

}
