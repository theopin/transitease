package com.transitease.bus.service;

import com.fasterxml.jackson.annotation.JsonAlias;

public record BusStopDTO (@JsonAlias("BusStopCode") String busStopCode,
                          @JsonAlias("RoadName") String roadName,
                          @JsonAlias("Description") String description,
                          @JsonAlias("Latitude") double latitude,
                          @JsonAlias("Longitude") double longitude
)
{}