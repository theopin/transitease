package com.transitease.controller;


import com.transitease.service.BusService;
import com.transitease.service.TaxiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/taxis")
public class TaxiController {

    private static final Logger LOGGER = LogManager.getLogger(TaxiController.class);

    @Autowired
    @Qualifier("taxiService")
    private TaxiService taxiServiceObject;


    @GetMapping("/stands")
    public Object getListOfTaxiStands() {

        return taxiServiceObject.getListOfTaxiStands();
    }
}