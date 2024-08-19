package com.transitease.dto.taxi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaxiStandDTO {

	@JsonProperty("TaxiCode")
	private String taxiCode;

	@JsonProperty("Latitude")
	private double latitude;

	@JsonProperty("Longitude")
	private double longitude;

	@JsonProperty("Bfa")
	private String bfa;

	@JsonProperty("Ownership")
	private String ownership;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("Name")
	private String name;
}