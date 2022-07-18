package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.exceptions.NotFoundException;
import com.dpc.M4_Summative_DPC.models.Game;
import com.dpc.M4_Summative_DPC.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class GameController {
    @Autowired
    ServiceLayer service;

    @GetMapping("/games")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames(@RequestParam(required=false) String title, @RequestParam(required = false) String esrbRating, @RequestParam(required = false) String studio) {
        List<Game> returnList = service.getAllGames();
        if (title != null) {
            returnList = returnList.stream()
                    .filter(g -> g.getTitle().equals(title))
                    .collect(Collectors.toList());
        }

        if (esrbRating != null) {
            returnList = returnList.stream()
                    .filter(g -> g.getEsrbRating().equals(esrbRating))
                    .collect(Collectors.toList());
        }

        if (studio != null) {
            returnList = returnList.stream()
                    .filter(g -> g.getStudio().equals(studio))
                    .collect(Collectors.toList());
        }

        return returnList;
    }

    @GetMapping("/games/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable int id) {
        Optional<Game> game = service.getGameById(id);
            if (!game.isPresent()) {
            throw new NotFoundException("Invalid id, enter the correct id.");
        }
            return game.get();

    }

    @GetMapping("/games/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByTitle(@PathVariable String title) {
        if (service.getGameByTitle(title).isEmpty()) {
            throw new NotFoundException("Game title not found in inventory.");
        }
        return service.getGameByTitle(title);
    }
    @GetMapping("/games/rating/{rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByEsrbRating(@PathVariable String rating) {
        if (service.getGamesByEsrbRating(rating).isEmpty()) {
            throw new NotFoundException("Rating does not exist.");
        }
        return service.getGamesByEsrbRating(rating);
    }

    @GetMapping("/games/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByStudio(@PathVariable String studio) {
        if (service.getGamesByStudio(studio).isEmpty()) {
            throw new NotFoundException("Studio does not exist.");
        }
        return service.getGamesByStudio(studio);
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game game) {
        return service.addGame(game);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody @Valid Game game) {
        service.updateGame(game);
    }

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        service.deleteGame(id);
    }
}
