package com.transitease.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bus")
public class BusController {

    private static final Logger LOGGER = LogManager.getLogger(BusController.class);


    @GetMapping("/arrival")
    public void getBusArrivalTime(@RequestParam String name, @RequestParam int age) {
        LOGGER.info("name: " + name);
        LOGGER.info("age: " + age);
    }


}