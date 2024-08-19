package com.transitease.dto.bus.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class BusServiceDTO {

	@JsonProperty("ServiceNo")
	private String serviceNo;

	@JsonProperty("Operator")
	private String operator;

	@JsonProperty("Direction")
	private int direction;

	@JsonProperty("Category")
	private String category;

	@JsonProperty("OriginCode")
	private String originCode;

	@JsonProperty("DestinationCode")
	private String destinationCode;

	@JsonProperty("AM_Peak_Freq")
	private String amPeakFreq;

	@JsonProperty("AM_Offpeak_Freq")
	private String amOffpeakFreq;

	@JsonProperty("PM_Peak_Freq")
	private String pmPeakFreq;

	@JsonProperty("PM_Offpeak_Freq")
	private String pmOffpeakFreq;

	@JsonProperty("LoopDesc")
	private String loopDesc;
}
