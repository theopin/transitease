package com.transitease.controller;


import com.transitease.dto.bus.arrival.BusServiceArrivalDTO;
import com.transitease.dto.bus.service.BusRouteDTO;
import com.transitease.dto.bus.service.BusServiceDTO;
import com.transitease.dto.bus.service.BusStopDTO;
import com.transitease.service.BusArrivalService;
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

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/buses")
public class BusController {

    private static final Logger LOGGER = LogManager.getLogger(BusController.class);


    @Autowired
    @Qualifier("busService")
    private BusService busServiceObject;

    @Autowired
    @Qualifier("busArrivalService")
    private BusArrivalService busArrivalServiceObject;


    @GetMapping("/services/details/{serviceNumber}")
    public List<BusServiceDTO> getBusServiceDetails(@PathVariable("serviceNumber") String serviceNumber) {
        LOGGER.info("service: " + serviceNumber);

        return busServiceObject.getBusServiceDetails(serviceNumber);
    }

    @GetMapping("/services/routes/{serviceNumber}")
    public List<BusRouteDTO> getBusRouteDetails(@PathVariable("serviceNumber") String serviceNumber) {
        LOGGER.info("service: " + serviceNumber);

        return busServiceObject.getBusRouteDetails(serviceNumber);
    }

    @GetMapping("/stops/details/{stopCode}")
    public List<BusStopDTO> getBusStopDetails(@PathVariable("stopCode") String stopCode) {
        LOGGER.info("busStopCode: " + stopCode);

        return busServiceObject.getBusStopDetails(stopCode);

    }

    @GetMapping("/stops/details/nearby")
    public List<BusStopDTO> getNearbyBusStops(@RequestParam("latitude") double latitude,
                                              @RequestParam("longitude") double longitude,
                                              @RequestParam("distance") double maxDistance) {
        LOGGER.info("lat, long: " + latitude + ", " + longitude);

        return busServiceObject.getBusStopsInRange(latitude, longitude, maxDistance);

    }


    @GetMapping("/services/arrivals/{serviceNumber}")
    public BusServiceArrivalDTO getBusArrivalTime(@RequestParam("busStopCode") String busStopCode,
                                                  @PathVariable("serviceNumber") String serviceNumber)
            throws ExecutionException, InterruptedException {
        LOGGER.info("busStopCode: " + busStopCode);
        LOGGER.info("service: " + serviceNumber);

        return busArrivalServiceObject.getBusArrivalTime(busStopCode, serviceNumber);
    }


    @GetMapping("/stops/arrivals/{stopCode}")
    public BusServiceArrivalDTO getArrivalsAtBusStop(@PathVariable("stopCode") String stopCode)
            throws ExecutionException, InterruptedException {
        LOGGER.info("busStopCode: " + stopCode);

        return busArrivalServiceObject.getArrivalsAtBusStop(stopCode);

    }
}