package com.transitease.taxi;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/taxi")
public class TaxiController {

    private static final Logger LOGGER = LogManager.getLogger(TaxiController.class);

    @Autowired
    @Qualifier("taxiService")
    private TaxiService taxiServiceObject;


    @GetMapping("/stands")
    public List<TaxiStandDTO> getListOfTaxiStands() {

        return taxiServiceObject.getListOfTaxiStands();
    }

    @GetMapping("/stands/{standCode}")
    public List<TaxiStandDTO> getArrivalsAtBusStop(@PathVariable("standCode") String standCode)
        throws ExecutionException, InterruptedException {
        LOGGER.info("StandCode: {0}" + standCode);

        return taxiServiceObject.getTaxiStandByCode(standCode);

    }
}