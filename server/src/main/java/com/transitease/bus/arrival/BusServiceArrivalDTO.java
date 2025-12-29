package com.transitease.bus.arrival;

import com.fasterxml.jackson.annotation.JsonAlias;

public record BusServiceArrivalDTO(@JsonAlias("odata.metadata") String odataMetadata,
                                   @JsonAlias("BusStopCode") String busStopCode,
                                   @JsonAlias("Services") BusServedServiceDTO[] services) {
}
