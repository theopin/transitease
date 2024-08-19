package com.transitease.dto.bus.arrival;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class BusServedServiceDTO {
	@JsonProperty("ServiceNo")
	private String serviceNo;

	@JsonProperty("Operator")
	private String operator;

	@JsonProperty("NextBus")
	private NextBusDTO nextBus;

	@JsonProperty("NextBus2")
	private NextBusDTO nextBus2;

	@JsonProperty("NextBus3")
	private NextBusDTO nextBus3;

}
