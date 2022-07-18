package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeedController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/seed")
    @ResponseStatus(HttpStatus.CREATED)
    public void seedData() {
        serviceLayer.clearDatabase();
        serviceLayer.seedTaxes();
        serviceLayer.seedFees();
        serviceLayer.seedGames();
        serviceLayer.seedConsole();
        serviceLayer.seedTShirts();
    }
}
