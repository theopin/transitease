package com.transitease.bus.service;

import com.fasterxml.jackson.annotation.JsonAlias;

public record BusServiceDTO(@JsonAlias("ServiceNo") String serviceNo,
                            @JsonAlias("Operator") String operator,
                            @JsonAlias("Direction") int direction,
                            @JsonAlias("Category") String category,
                            @JsonAlias("OriginCode") String originCode,
                            @JsonAlias("DestinationCode") String destinationCode,
                            @JsonAlias("AM_Peak_Freq") String amPeakFreq,
                            @JsonAlias("AM_Offpeak_Freq") String amOffpeakFreq,
                            @JsonAlias("PM_Peak_Freq") String pmPeakFreq,
                            @JsonAlias("PM_Offpeak_Freq") String pmOffpeakFreq,
                            @JsonAlias("LoopDesc") String loopDesc) {

}
