package com.transitease.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transitease.dto.BusServiceDTO;
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

	private ObjectMapper busObjectMapper = new ObjectMapper();

	private static final Logger LOGGER = LogManager.getLogger(BusService.class);


	public Object getBusServiceDetails(String serviceNumber) {
		List<Object> busServiceDataCache = dataCacheService.getDataByKey(CacheEndpoints.BUS_SERVICES.getEndpoint());

		List<BusServiceDTO> result = new ArrayList<>();

		// Iterate through the cached data to find the specific bus service
//			for (Object busObject : busServiceDataCache) {
//				LOGGER.info(busObject);
//				busObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//				BusServiceDTO busService = busObjectMapper.convertValue(busObject, BusServiceDTO.class);
//
//				if (busService.getServiceNo().equals(serviceNumber)) {
//					result.add(busService);
//				}
//			}

		return result;
	}

	public Object getBusRouteDetails(String serviceNumber) {
		return dataCacheService.getDataByKey(CacheEndpoints.BUS_ROUTES.getEndpoint());
	}

	public Object getBusStopDetails(String busStopCode) {
		return dataCacheService.getDataByKey(CacheEndpoints.BUS_STOPS.getEndpoint());
	}

}
