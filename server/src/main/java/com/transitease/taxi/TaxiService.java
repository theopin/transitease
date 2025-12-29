package com.transitease.taxi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transitease.cache.CacheEndpoint;
import com.transitease.cache.DataCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("taxiService")
public class TaxiService {

	@Autowired
	@Qualifier("dataCacheService")
	private DataCacheService dataCacheService;

	private final ObjectMapper taxiObjectMapper = new ObjectMapper();

	public List<TaxiStandDTO> getListOfTaxiStands() {

		List<Object> taxiStandDataCache = dataCacheService.getDataByKey(CacheEndpoint.TAXI_STANDS);

		List<TaxiStandDTO> result = new ArrayList<>();

		for (Object taxiObject : taxiStandDataCache) {
            TaxiStandDTO taxiStand = taxiObjectMapper.convertValue(taxiObject, TaxiStandDTO.class);

			result.add(taxiStand);
		}

		return result;
	}

	public List<TaxiStandDTO> getTaxiStandByCode(String standCode) {

		List<Object> taxiStandDataCache = dataCacheService.getDataByKey(CacheEndpoint.TAXI_STANDS);

		List<TaxiStandDTO> result = new ArrayList<>();

		for (Object taxiObject : taxiStandDataCache) {
            TaxiStandDTO taxiStand = taxiObjectMapper.convertValue(taxiObject, TaxiStandDTO.class);


			if (taxiStand.taxiCode().equals(standCode)) {
				result.add(taxiStand);
			}

		}

		return result;
	}

}
