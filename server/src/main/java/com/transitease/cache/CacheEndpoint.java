package com.transitease.cache;

import lombok.Getter;

@Getter
public enum CacheEndpoint {
	BUS_SERVICES("BusServices"),
	BUS_ROUTES("BusRoutes"),
	BUS_STOPS("BusStops"),
	TAXI_STANDS("TaxiStands");

	private final String endpoint;

	CacheEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

}
