package com.transitease.bus.arrival;

import com.fasterxml.jackson.annotation.JsonAlias;

public record BusRouteDTO(@JsonAlias("ServiceNo") String serviceNo,
                          @JsonAlias("Operator") String operator,
                          @JsonAlias("Direction") int direction,
                          @JsonAlias("StopSequence") int stopSequence,
                          @JsonAlias("BusStopCode") String busStopCode,
                          @JsonAlias("Distance") double distance,
                          @JsonAlias("WD_FirstBus") String wdFirstBus,
                          @JsonAlias("WD_LastBus") String wdLastBus,
                          @JsonAlias("SAT_FirstBus") String satFirstBus,
                          @JsonAlias("SAT_LastBus") String satLastBus,
                          @JsonAlias("SUN_FirstBus") String sunFirstBus,
                          @JsonAlias("SUN_LastBus") String sunLastBus) {

}
