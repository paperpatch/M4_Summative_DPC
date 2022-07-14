package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.exceptions.NotFoundException;
import com.dpc.M4_Summative_DPC.models.Console;
import com.dpc.M4_Summative_DPC.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {
    @Autowired
    ServiceLayer service;

    // Create a console
    @PostMapping("/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody Console console) {
        return service.addConsole(console);
    }
    //    Get all console
    @GetMapping("/console")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsole(@RequestParam(required = false) String manufacturer) {
        return service.getAllConsole();
    }
    //    Get console by manufacturer
    @GetMapping("/console/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        if (service.getConsoleByManufacturer(manufacturer) == null) {
            throw new NotFoundException("Manufacturer not found.");
        }
       return service.getConsoleByManufacturer(manufacturer);
    }
    //    Get console by id
    @GetMapping("/console/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable int id) {
        Optional<Console> console = service.getConsoleById(id);
        if (!console.isPresent()) {
            throw new IllegalArgumentException("Invalid id, enter the correct id.");
        }
         return console.get();
    }
    //    Update a console
    @PutMapping("console")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAConsole(@RequestBody Console console) {
//        if (console.getConsoleId() == null) {
//            console.setConsoleId(id);
//        } else if (console.getConsoleId() != id) {
//
//            throw new IllegalArgumentException("Invalid id, enter the correct id.");
//        }
        service.updateConsole(console);
    }
    //    Delete console
    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroyConsole(@PathVariable int id) {
        service.deleteConsole(id);
    }



}
