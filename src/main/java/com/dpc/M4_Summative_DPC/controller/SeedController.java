package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.models.Console;
import com.dpc.M4_Summative_DPC.models.Game;
import com.dpc.M4_Summative_DPC.models.TShirt;
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

        serviceLayer.addGame(new Game("Elden Ring", "M (Mature 17+)", "Elden Ring sees you play as an initially meaningless character in a world of monsters and demigods, all struggling for control over the Lands Between.", 59.95, "FromSoftware Inc.", 1));
        serviceLayer.addGame(new Game("LEGO Star Wars: The Skywalker Saga", "E (Everyone)", "Lego-themed action-adventure game.", 49.00, "Warner Bros. Interactive Entertainment", 2));
        serviceLayer.addGame(new Game("Among Us", "E (Everyone)", "Online multiplayer social deduction game.", 4.99, "InnerSloth LLC", 5));

        serviceLayer.addConsole(new Console("Playstation 5", "Sony", "1 TB", "AMD Zen 2-CPU", 549.99, 20));
        serviceLayer.addConsole(new Console("Xbox Series X", "Microsoft", "1 TB", "AMD Zen 2-CPU", 499.99, 10));
        serviceLayer.addConsole(new Console("Nintendo Switch", "Nintendo", "32 GB", "Quad-core ARM Cortex", 299.99, 15));

        serviceLayer.saveTShirt(new TShirt("small", "red", "small red shirt", 9.99, 14));
        serviceLayer.saveTShirt(new TShirt("medium", "blue", "medium sized blue shirt", 9.99, 30));
        serviceLayer.saveTShirt(new TShirt("large", "green", "large sized green shirt", 9.99, 21));
    }
}
