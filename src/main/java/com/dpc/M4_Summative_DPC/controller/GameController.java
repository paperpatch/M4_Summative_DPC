package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.models.Game;
import com.dpc.M4_Summative_DPC.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class GameController {
    @Autowired
    GameRepository gameRepository;

    @GetMapping("/games")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGames(@RequestParam(required=false) String title, @RequestParam(required = false) String esrbRating, @RequestParam(required = false) String studio) {
        List<Game> returnList = gameRepository.findAll();
        if (title != null) {
            returnList = returnList.stream()
                    .filter(g -> g.getTitle().contains(title))
                    .collect(Collectors.toList());
        }

        if (esrbRating != null) {
            returnList = returnList.stream()
                    .filter(g -> g.getEsrbRating().contains(esrbRating))
                    .collect(Collectors.toList());
        }

        if (studio != null) {
            returnList = returnList.stream()
                    .filter(g -> g.getEsrbRating().contains(studio))
                    .collect(Collectors.toList());
        }

        return returnList;
    }

    @GetMapping("/games/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable int id) {
        Optional<Game> returnVal = gameRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

//    @GetMapping("/games/{title}")
//    @ResponseStatus(HttpStatus.OK)
//    public Optional<Game> getGameByTitle(@PathVariable String title) {
//        Optional<Game> returnVal = gameRepository.findByTitle(title);
//        if (returnVal.isPresent()) {
//            return returnVal.get();
//        } else {
//            return null;
//        }
//    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game game) {
        return gameRepository.save(game);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game) {
        gameRepository.save(game);
    }

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        gameRepository.deleteById(id);
    }
}
