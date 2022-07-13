package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.models.Console;
import com.dpc.M4_Summative_DPC.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {
    @Autowired
    ConsoleRepository consoleRepository;

    // Create a console
    @RequestMapping(value = "/console", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody Console customer) {
        return consoleRepository.save(customer);
    }
    //    Get all console
    @RequestMapping(value = "/console", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsole(@RequestParam(required = false) String manufacturer) {
        if (manufacturer != null) {
            return consoleRepository.findByManufacturer(manufacturer);
        }
        return consoleRepository.findAll();
    }
    //    Get console by id
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Console getCConsoleById(@PathVariable int id) {
        Optional<Console> console = consoleRepository.findById(id);
        if (!console.isPresent()) {
            throw new IllegalArgumentException("Invalid id, enter the correct id.");
        }
        return console.get();
    }
    //    Update a customer
    @RequestMapping(value = "console/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAConsole(@PathVariable int id, @RequestBody Console console) {
        if (console.getConsoleId() == null) {
            console.setConsoleId(id);
        } else if (console.getConsoleId() != id) {

            throw new IllegalArgumentException("Invalid id, enter the correct id.");
        }
        consoleRepository.save(console);
    }
    //    Delete customer
    @RequestMapping(value = "/console/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroyConsole(@PathVariable int id) {
        consoleRepository.deleteById(id);
    }



}
