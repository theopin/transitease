package com.transitease.service;

import lombok.Getter;

@Getter
public enum CacheEndpoints {
	BUS_SERVICES("BusServices"),
	BUS_ROUTES("BusRoutes"),
	BUS_STOPS("BusStops"),
	TAXI_STANDS("TaxiStands");

	private final String endpoint;

	CacheEndpoints(String endpoint) {
		this.endpoint = endpoint;
	}

}
