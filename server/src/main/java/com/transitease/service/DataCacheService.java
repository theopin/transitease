package com.transitease.service;

import com.transitease.service.response.CacheTransportResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

@Service("dataCacheService")
public class DataCacheService {

	private static final Logger LOGGER = LogManager.getLogger(DataCacheService.class);
	private static final ConcurrentMap<String, List<Object>> dataCache = new ConcurrentHashMap<>();

	@Autowired
	@Qualifier("transportApiService")
	private TransportApiService transportApiService;

	@Scheduled(fixedDelay = 43200000) // Run 12 hours after the previous execution completes
	private void refreshData() {
		LOGGER.info("Running data cache refresh operation");
		int apiCalls = 0;

		for (CacheEndpoints endpoint : CacheEndpoints.values()) {
			String iteratedEndpoint = endpoint.getEndpoint();

			List<Object> combinedData = new ArrayList<>();
			int fetchedCount;
			int skippedCount = 0;

			try {
				do {
					CacheTransportResponse fetchResult = accumulateEndpointDataset(iteratedEndpoint, skippedCount);

					if (fetchResult.getValue() != null && !fetchResult.getValue().isEmpty()) {
						fetchedCount = fetchResult.getValue().size();
						LOGGER.info("FetchResult Count: " + fetchedCount);
						combinedData.addAll(fetchResult.getValue());

						skippedCount += 500;
					} else {
						fetchedCount = 0;
					}

					apiCalls += 1;
				} while (fetchedCount == 500);

				LOGGER.info(MessageFormat.format("Skip and Final count for {0}: {1} skip, {2} final",
					iteratedEndpoint,
					String.valueOf(skippedCount),
					combinedData.size()));

				LOGGER.info(combinedData.get(0));
				dataCache.put(iteratedEndpoint, combinedData);



			} catch (Exception e) {
				LOGGER.error("Failed to fetch api. putting whatever is fetched for " + iteratedEndpoint, e);
				LOGGER.error("API Count: " + apiCalls);

				dataCache.put(iteratedEndpoint, combinedData);
			}

		}
		LOGGER.info("Data cache refresh complete");
		LOGGER.info("API Count: " + apiCalls);
	}

	private CacheTransportResponse accumulateEndpointDataset(String iteratedEndpoint, int skippedCount)
			throws InterruptedException, ExecutionException {
		return (CacheTransportResponse)  transportApiService
			.makeGetRequest("/"
				+ iteratedEndpoint
				+ "?$skip="
				+ skippedCount,
				CacheTransportResponse.class)
			.get();
	}

	public List<Object> getDataByKey(String keyName) {
		return dataCache.getOrDefault(keyName, new ArrayList<>());
	}
}