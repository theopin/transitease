package com.transitease.bus.arrival;

import com.fasterxml.jackson.annotation.JsonAlias;

public record BusServedServiceDTO(@JsonAlias("ServiceNo") String serviceNo,
                                  @JsonAlias("Operator") String operator,
                                  @JsonAlias("NextBus") NextBusDTO nextBus,
                                  @JsonAlias("NextBus2") NextBusDTO nextBus2,
                                  @JsonAlias("NextBus3") NextBusDTO nextBus3) {
}
