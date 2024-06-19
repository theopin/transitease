package com.transitease.controller;


import com.transitease.service.TaxiService;
import com.transitease.service.TrainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/trains")
public class TrainController {

    private static final Logger LOGGER = LogManager.getLogger(TrainController.class);

    @Autowired
    @Qualifier("trainService")
    private TrainService trainServiceObject;


    @GetMapping("/alerts")
    public Object getListOfTaxiStands() {

        return trainServiceObject.getTrainServiceAlerts();
    }
}