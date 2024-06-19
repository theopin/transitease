package com.transitease.controller;


import com.transitease.service.BusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/buses")
public class BusController {

    private static final Logger LOGGER = LogManager.getLogger(BusController.class);


    @Autowired
    @Qualifier("busService")
    private BusService busServiceObject;


    @GetMapping("/services/details/{serviceNumber}")
    public Object getBusServiceDetails(@PathVariable("serviceNumber") String serviceNumber) {
        LOGGER.info("service: " + serviceNumber);

        return busServiceObject.getBusServiceDetails(serviceNumber);
    }

    @GetMapping("/services/routes/{serviceNumber}")
    public Object getBusRouteDetails(@PathVariable("serviceNumber") String serviceNumber) {
        LOGGER.info("service: " + serviceNumber);

        return busServiceObject.getBusRouteDetails(serviceNumber);
    }

    @GetMapping("/services/arrivals/{serviceNumber}")
    public Object getBusArrivalTime(@RequestParam("busStopCode") String busStopCode,
                                    @PathVariable("serviceNumber") String serviceNumber) {
        LOGGER.info("busStopCode: " + busStopCode);
        LOGGER.info("service: " + serviceNumber);

        return busServiceObject.getBusArrivalTime(busStopCode, serviceNumber);
    }

    @GetMapping("/stops/details/{stopCode}")
    public Object getBusStopDetails(@PathVariable("stopCode") String stopCode) {
        LOGGER.info("busStopCode: " + stopCode);

        return busServiceObject.getBusStopDetails(stopCode);

    }

    @GetMapping("/stops/arrivals/{stopCode}")
    public Object getArrivalsAtBusStop(@PathVariable("stopCode") String stopCode) {
        LOGGER.info("busStopCode: " + stopCode);

        return busServiceObject.getArrivalsAtBusStop(stopCode);

    }
}