package com.transitease.dto.bus.arrival;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class BusServiceArrivalDTO {
	 @JsonProperty("odata.metadata")
	 String odataMetadata;

	 @JsonProperty("BusStopCode")
	 String busStopCode;

	 @JsonProperty("Services")
	 BusServedServiceDTO[] services;
}
