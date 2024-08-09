package com.transitease.controller;


import com.transitease.dto.taxi.TaxiStandDTO;
import com.transitease.service.TaxiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/taxis")
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
        LOGGER.info("StandCode: " + standCode);

        return taxiServiceObject.getTaxiStandByCode(standCode);

    }
}