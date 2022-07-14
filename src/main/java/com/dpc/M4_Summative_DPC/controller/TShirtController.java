package com.dpc.M4_Summative_DPC.controller;



import com.dpc.M4_Summative_DPC.exceptions.NotFoundException;
import com.dpc.M4_Summative_DPC.models.TShirt;
import com.dpc.M4_Summative_DPC.repository.TShirtRepository;
import com.dpc.M4_Summative_DPC.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TShirtController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody TShirt tShirt) {
        return serviceLayer.saveTShirt(tShirt);
    }

    @GetMapping("/tshirt")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirt() {
       return serviceLayer.findAllTshirt();
    }

    @GetMapping("/tshirt/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirtByColor(@PathVariable("color") String color) {
        if(serviceLayer.findAllTshirtByColor(color) == null){
            throw new NotFoundException("no t-shirt left with this color");
        }
        return serviceLayer.findAllTshirtByColor(color);
    }

    @GetMapping("/tshirt/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirtBySize(@PathVariable("size")String size) {
        if(serviceLayer.findAllTshirtByColor(size) == null){
            throw new NotFoundException("no t-shirt left with this size");
        }
        return serviceLayer.findAllTshirtBySize(size);
    }

    @GetMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TShirt> findTShirtById(@PathVariable int id) throws Exception{
        if(serviceLayer.findATShirtById(id) == null) {
            throw new NotFoundException("no t-shirt found!");
        }
       return serviceLayer.findATShirtById(id);
    }

    @PutMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@PathVariable int id, @RequestBody TShirt tShirt) {
        if(serviceLayer.findATShirtById(id) == null) {
            throw new NotFoundException("no t-shirt found!");
        }
        serviceLayer.updateTShirt(tShirt);
    }

    @DeleteMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable int id) {
        if(serviceLayer.findATShirtById(id) == null) {
            throw new NotFoundException("no t-shirt found!");
        }
        serviceLayer.removeTShirt(id);
    }



}
