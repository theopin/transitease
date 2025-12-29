package com.transitease.taxi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transitease.cache.CacheEndpoint;
import com.transitease.cache.DataCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("taxiService")
public class TaxiService {

	@Autowired
	@Qualifier("dataCacheService")
	private DataCacheService dataCacheService;

	private final ObjectMapper taxiObjectMapper = new ObjectMapper();

	public List<TaxiStandDTO> getListOfTaxiStands() {

		List<Object> taxiStandDataCache = dataCacheService.getDataByKey(CacheEndpoint.TAXI_STANDS);

        return taxiStandDataCache.stream()
                .map(taxiObject -> taxiObjectMapper.convertValue(taxiObject, TaxiStandDTO.class))
                .collect(Collectors.toList());
	}

	public List<TaxiStandDTO> getTaxiStandByCode(String standCode) {

		List<Object> taxiStandDataCache = dataCacheService.getDataByKey(CacheEndpoint.TAXI_STANDS);

		List<TaxiStandDTO> result = new ArrayList<>();

        return taxiStandDataCache.stream()
                .map(taxiObject -> taxiObjectMapper.convertValue(taxiObject, TaxiStandDTO.class))
                .filter(taxiStand -> standCode.equals(taxiStand.taxiCode()))
                .collect(Collectors.toList());
	}

}
