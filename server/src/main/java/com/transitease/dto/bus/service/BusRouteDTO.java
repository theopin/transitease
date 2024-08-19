package com.transitease.dto.bus.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BusRouteDTO {

	@JsonProperty("ServiceNo")
	private String serviceNo;

	@JsonProperty("Operator")
	private String operator;

	@JsonProperty("Direction")
	private int direction;

	@JsonProperty("StopSequence")
	private int stopSequence;

	@JsonProperty("BusStopCode")
	private String busStopCode;

	@JsonProperty("Distance")
	private double distance;

	@JsonProperty("WD_FirstBus")
	private String wdFirstBus;

	@JsonProperty("WD_LastBus")
	private String wdLastBus;

	@JsonProperty("SAT_FirstBus")
	private String satFirstBus;

	@JsonProperty("SAT_LastBus")
	private String satLastBus;

	@JsonProperty("SUN_FirstBus")
	private String sunFirstBus;

	@JsonProperty("SUN_LastBus")
	private String sunLastBus;
}
