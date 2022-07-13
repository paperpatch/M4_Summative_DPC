package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.models.TShirt;
import com.dpc.M4_Summative_DPC.repository.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TShirtController {
    @Autowired
    TShirtRepository tShirtRepository;

    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody TShirt tshirt) {return tShirtRepository.save(tshirt);}

    @GetMapping("/tshirt")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirt(@RequestParam(required=false) String color,@RequestParam(required=false) String size) {
        if(color !=null){
            return tShirtRepository.findByColor(color);
        }
        if(size !=null){
            return tShirtRepository.findBySize(size);
        }
        return tShirtRepository.findAll();
    }

    @GetMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirt findTShirtById(@PathVariable int id){
        Optional<TShirt> tShirt = tShirtRepository.findById(id);

        if(tShirt.isPresent() == false){
            throw new IllegalArgumentException("There's no T-Shirt with this ID");
        }
        return tShirt.get();
    }




}
