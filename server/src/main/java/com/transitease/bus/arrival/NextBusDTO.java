package com.transitease.bus.arrival;

import com.fasterxml.jackson.annotation.JsonAlias;

public record NextBusDTO(@JsonAlias("OriginCode") String originCode,
                         @JsonAlias("DestinationCode") String destinationCode,
                         @JsonAlias("EstimatedArrival") String estimatedArrival,
                         @JsonAlias("Latitude") String latitude,
                         @JsonAlias("Longitude") String longitude,
                         @JsonAlias("VisitNumber") String visitNumber,
                         @JsonAlias("Load") String load,
                         @JsonAlias("Feature") String feature,
                         @JsonAlias("Type") String type) {
}