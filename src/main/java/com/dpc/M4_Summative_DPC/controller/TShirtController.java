package com.dpc.M4_Summative_DPC.controller;



import com.dpc.M4_Summative_DPC.repository.TShirtRepository;
import com.dpc.M4_Summative_DPC.service.ServiceLayer;
import com.dpc.M4_Summative_DPC.viewmodel.TShirtViewModel;
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
    public TShirtViewModel addTShirt(@RequestBody TShirtViewModel tShirtViewModel) {
        return serviceLayer.saveTShirt(tShirtViewModel);
    }

    @GetMapping("/tshirt")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getAllTShirt() {
       return serviceLayer.findAllTshirt();
    }

    @GetMapping("/tshirt/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getAllTShirtByColor(@PathVariable("color") String color) {
        return serviceLayer.findAllTshirtByColor(color);
    }

    @GetMapping("/tshirt/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getAllTShirtBySize(@PathVariable("size")String size) {
        return serviceLayer.findAllTshirtBySize(size);
    }

    @GetMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirtViewModel findTShirtById(@PathVariable int id){
       return serviceLayer.findATShirtById(id);
    }

    @PutMapping("/tshirt")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody TShirtViewModel tShirtViewModel) {
        serviceLayer.updateTShirt(tShirtViewModel);
    }

    @DeleteMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable int id) {
        serviceLayer.removeTShirt(id);
    }



}
