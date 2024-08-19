package com.transitease.dto.bus.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BusStopDTO {

	@JsonProperty("BusStopCode")
	private String busStopCode;

	@JsonProperty("RoadName")
	private String roadName;

	@JsonProperty("Description")
	private String description;

	@JsonProperty("Latitude")
	private double latitude;

	@JsonProperty("Longitude")
	private double longitude;
}