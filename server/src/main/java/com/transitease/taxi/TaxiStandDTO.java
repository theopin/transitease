package com.transitease.taxi;

import com.fasterxml.jackson.annotation.JsonAlias;

public record TaxiStandDTO(@JsonAlias("TaxiCode") String taxiCode,
                           @JsonAlias("Latitude") double latitude,
                           @JsonAlias("Longitude") double longitude,
                           @JsonAlias("Bfa") String bfa,
                           @JsonAlias("Ownership") String ownership,
                           @JsonAlias("Type") String type,
                           @JsonAlias("Name") String name) {

}