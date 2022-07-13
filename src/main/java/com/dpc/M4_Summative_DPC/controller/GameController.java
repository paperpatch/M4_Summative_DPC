package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.model.Game;
import com.dpc.M4_Summative_DPC.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    GameRepository repo;

    @GetMapping("/games")
    public List<Game> getGames() {
        return repo.findAll();
    }

    @GetMapping("/games/{id}")
    public Game getGameById(@PathVariable int id) {
        Optional<Game> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game artist) {
        return repo.save(artist);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game artist) {
        repo.save(artist);
    }

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        repo.deleteById(id);
    }
}
