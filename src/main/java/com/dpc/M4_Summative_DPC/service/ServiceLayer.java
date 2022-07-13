package com.dpc.M4_Summative_DPC.service;

import com.dpc.M4_Summative_DPC.models.Game;
import com.dpc.M4_Summative_DPC.repository.GameRepository;
import com.dpc.M4_Summative_DPC.repository.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {

    private TShirtRepository tShirtRepository;
    private GameRepository gameRepository;

    @Autowired
    public ServiceLayer(TShirtRepository tShirtRepository, GameRepository gameRepository) {
        this.tShirtRepository = tShirtRepository;
        this.gameRepository = gameRepository;
    }

    // Game CRUD
    public List<Game> getAllGames() {return gameRepository.findAll(); }

    public Optional<Game> getGameById(int id) { return gameRepository.findById(id); }

    public Optional<Game> getGameByTitle(String title) { return gameRepository.findByTitle(title); }

    public List<Game> getGamesByEsrbRating(String esrbRating) { return gameRepository.findByEsrbRating(esrbRating); }

    public List<Game> getGamesByStudio(String studio) { return gameRepository.findByStudio(studio); }

    public Game addGame(Game game) { return gameRepository.save(game); }

    public void updateGame(Game game) { gameRepository.save(game); }

    public void deleteGame(int id) { gameRepository.deleteById(id); }

}
