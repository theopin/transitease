package com.transitease.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transitease.dto.bus.service.BusRouteDTO;
import com.transitease.dto.bus.service.BusServiceDTO;
import com.transitease.dto.bus.service.BusStopDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("busService")
public class BusService {

	@Autowired
	@Qualifier("dataCacheService")
	private DataCacheService dataCacheService;

	private final ObjectMapper busObjectMapper = new ObjectMapper();

	private static final Logger LOGGER = LogManager.getLogger(BusService.class);


	public List<BusServiceDTO> getBusServiceDetails(String serviceNumber) {
		List<Object> busServiceDataCache = dataCacheService.getDataByKey(CacheEndpoints.BUS_SERVICES.getEndpoint());

		List<BusServiceDTO> result = new ArrayList<>();

		// Iterate through the cached data to find the specific bus service
			for (Object busObject : busServiceDataCache) {
				BusServiceDTO busService = busObjectMapper.convertValue(busObject, BusServiceDTO.class);

				if (busService.getServiceNo().equals(serviceNumber)) {
					result.add(busService);
				}
			}

		return result;
	}

	public List<BusRouteDTO> getBusRouteDetails(String serviceNumber) {
		List<Object> busRoutesDataCache = dataCacheService.getDataByKey(CacheEndpoints.BUS_ROUTES.getEndpoint());

		List<BusRouteDTO> result = new ArrayList<>();

		for (Object busObject : busRoutesDataCache) {
			BusRouteDTO busRoute = busObjectMapper.convertValue(busObject, BusRouteDTO.class);

			if (busRoute.getServiceNo().equals(serviceNumber)) {
				result.add(busRoute);
			}
		}

		return result;
	}

	public List<BusStopDTO> getBusStopDetails(String busStopCode) {

		List<Object> busStopDataCache = dataCacheService.getDataByKey(CacheEndpoints.BUS_STOPS.getEndpoint());

		List<BusStopDTO> result = new ArrayList<>();

		for (Object busObject : busStopDataCache) {
			BusStopDTO busStop = busObjectMapper.convertValue(busObject, BusStopDTO.class);

			if (busStop.getBusStopCode().equals(busStopCode)) {
				result.add(busStop);
			}
		}

		return result;

	}

}
