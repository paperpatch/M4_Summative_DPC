package com.dpc.M4_Summative_DPC.service;

import com.dpc.M4_Summative_DPC.models.Console;
import com.dpc.M4_Summative_DPC.models.Game;
import com.dpc.M4_Summative_DPC.repository.ConsoleRepository;
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
    private ConsoleRepository consoleRepository;

    @Autowired
    public ServiceLayer(TShirtRepository tShirtRepository, GameRepository gameRepository, ConsoleRepository consoleRepository) {
        this.tShirtRepository = tShirtRepository;
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
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
    public Console addConsole(Console console){
        return consoleRepository.save(console);
    }
    public List<Console> getConsoleByManufacturer(String manufacturer) {
        return consoleRepository.findByManufacturer(manufacturer); }
    public List<Console> getAllConsole (){
        return consoleRepository.findAll();
    }
    public Optional<Console> getConsoleById(int id) {
        return consoleRepository.findById(id);
    }
    public void updateConsole(Console console){
        consoleRepository.save(console);
    }
    public void deleteConsole(int id){
        consoleRepository.deleteById(id);
    }

}
