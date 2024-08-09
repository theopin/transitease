package com.transitease.dto.bus.arrival;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.*;

@Data
public class NextBusDTO {
	@JsonProperty("OriginCode")
	private String originCode;

	@JsonProperty("DestinationCode")
	private String destinationCode;

	@JsonProperty("EstimatedArrival")
	private String estimatedArrival;

	@JsonProperty("Latitude")
	private String latitude;

	@JsonProperty("Longitude")
	private String longitude;

	@JsonProperty("VisitNumber")
	private String visitNumber;

	@JsonProperty("Load")
	private String load;

	@JsonProperty("Feature")
	private String feature;

	@JsonProperty("Type")
	private String type;
}