package com.transitease.bus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transitease.bus.arrival.BusRouteDTO;
import com.transitease.bus.service.BusServiceDTO;
import com.transitease.bus.service.BusStopDTO;
import com.transitease.cache.CacheEndpoint;
import com.transitease.cache.DataCacheService;
import com.transitease.taxi.TaxiStandDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("busService")
public class BusService {

	@Autowired
	@Qualifier("dataCacheService")
	private DataCacheService dataCacheService;

	private final ObjectMapper busObjectMapper = new ObjectMapper();

	private static final Logger LOGGER = LogManager.getLogger(BusService.class);
	private static final int EARTH_RADIUS = 6371000; // Radius in meters



	public List<BusServiceDTO> getBusServiceDetails(String serviceNumber) {
		List<Object> busServiceDataCache = dataCacheService.getDataByKey(CacheEndpoint.BUS_SERVICES);

		return busServiceDataCache.stream()
                .map(busServiceObject -> busObjectMapper.convertValue(busServiceObject, BusServiceDTO.class))
                .filter(busService -> serviceNumber.equals(busService.serviceNo()))
                .collect(Collectors.toList());

	}

	public List<BusStopDTO> getBusStopDetails(String busStopCode) {

		List<Object> busStopDataCache = dataCacheService.getDataByKey(CacheEndpoint.BUS_STOPS);

        return busStopDataCache.stream()
                .map(busStopObject -> busObjectMapper.convertValue(busStopObject, BusStopDTO.class))
                .filter(busStop -> busStopCode.equals(busStop.busStopCode()))
                .collect(Collectors.toList());

	}

    public List<BusRouteDTO> getBusRouteDetails(String serviceNumber) {
        List<Object> busRoutesDataCache = dataCacheService.getDataByKey(CacheEndpoint.BUS_ROUTES);

        List<BusRouteDTO> result = new ArrayList<>();

        return busRoutesDataCache.stream()
                .map(busRouteObject -> busObjectMapper.convertValue(busRouteObject, BusRouteDTO.class))
                .filter(busRoute -> serviceNumber.equals(busRoute.serviceNo()))
                .collect(Collectors.toList());
    }


	public List<BusStopDTO> getBusStopsInRange(double latitude, double longitude, double maxDistanceMeters) {
		List<Object> busStopDataCache = dataCacheService.getDataByKey(CacheEndpoint.BUS_STOPS);
		List<BusStopDTO> result = new ArrayList<>();

        return busStopDataCache.stream()
                .map(busStopObject -> busObjectMapper.convertValue(busStopObject, BusStopDTO.class))
                .filter(busStop -> calculateDistance(latitude, longitude, busStop.latitude(), busStop.longitude()) <= maxDistanceMeters)
                .collect(Collectors.toList());

	}

	private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
			+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
			* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c;
	}
}
