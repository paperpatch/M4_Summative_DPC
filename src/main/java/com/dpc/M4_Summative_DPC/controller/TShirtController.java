package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.exceptions.NotFoundException;
import com.dpc.M4_Summative_DPC.models.TShirt;
import com.dpc.M4_Summative_DPC.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TShirtController {
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody @Valid TShirt tShirt) {
        return serviceLayer.saveTShirt(tShirt);
    }

    @GetMapping("/tshirt")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirt(@RequestParam(required=false) String size, @RequestParam(required = false) String color) {
        List<TShirt> returnList = serviceLayer.findAllTshirt();

        if (size != null) {
            returnList = returnList.stream()
                    .filter(g -> g.getSize().equals(size))
                    .collect(Collectors.toList());
        }

        if (color != null) {
            returnList = returnList.stream()
                    .filter(g -> g.getColor().equals(color))
                    .collect(Collectors.toList());
        }

        return returnList;
    }

    @GetMapping("/tshirt/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirtByColor(@PathVariable String color) {
        if(serviceLayer.findAllTshirtByColor(color).isEmpty()){
            throw new NotFoundException("no t-shirt left with this color");
        }
        return serviceLayer.findAllTshirtByColor(color);
    }

    @GetMapping("/tshirt/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirtBySize(@PathVariable String size) {
        if(serviceLayer.findAllTshirtBySize(size).isEmpty()){
            throw new NotFoundException("no t-shirt left with this size");
        }
        return serviceLayer.findAllTshirtBySize(size);
    }

    @GetMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirt findTShirtById(@PathVariable int id) throws Exception{
        Optional<TShirt> tshirt = serviceLayer.findATShirtById(id);
        if(!tshirt.isPresent()) {
            throw new NotFoundException("Could not found, enter the correct id.!");
        }
       return tshirt.get();
    }

    @PutMapping("/tshirt")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody @Valid TShirt tShirt) {
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
