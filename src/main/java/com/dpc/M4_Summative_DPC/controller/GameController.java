package com.dpc.M4_Summative_DPC.controller;

import com.dpc.M4_Summative_DPC.exceptions.NotFoundException;
import com.dpc.M4_Summative_DPC.models.Game;
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
public class GameController {
    @Autowired
    ServiceLayer service;

    @GetMapping("/games")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames(@RequestParam(required=false) String title, @RequestParam(required = false) String esrbRating, @RequestParam(required = false) String studio) {
        List<Game> returnList = service.getAllGames();
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
        Optional<Game> returnVal = service.getGameById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/games/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Game> getGameByTitle(@PathVariable String title) {
        if (service.getGameByTitle(title) == null) {
            throw new NotFoundException("Game title not found in inventory.");
        }
        return service.getGameByTitle(title);
    }

    @GetMapping("/games/rating/{rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByEsrbRating(@PathVariable String esrbRating) {
        if (service.getGameByTitle(esrbRating) == null) {
            throw new NotFoundException("Rating does not exist.");
        }
        return service.getGamesByEsrbRating(esrbRating);
    }

    @GetMapping("/games/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGamesByStudio(@PathVariable String studio) {
        if (service.getGamesByStudio(studio) == null) {
            throw new NotFoundException("Rating does not exist.");
        }
        return service.getGamesByStudio(studio);
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game game) {
        return service.addGame(game);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game) {
        service.updateGame(game);
    }

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        service.deleteGame(id);
    }
}
