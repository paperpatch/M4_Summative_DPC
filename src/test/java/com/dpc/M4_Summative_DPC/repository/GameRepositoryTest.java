package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.models.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;
    Game testGame;

    @Before
    public void setUp() throws Exception {
        gameRepository.deleteAll();
        testGame = new Game();
        testGame.setTitle("Neighbours from Hell");
        testGame.setEsrbRating("E");
        testGame.setDescription("puzzle strategy game");
        testGame.setPrice(55.99);
        testGame.setStudio("JoWooD Productions");
        testGame.setQuantity(234);
        testGame = gameRepository.save(testGame);
    }

    @Test
    public void addGetDeleteGame(){
        Optional<Game> gameList = gameRepository.findById(testGame.getId());
        assertEquals(gameList.get(), testGame);
        gameRepository.deleteById(testGame.getId());
        gameList = gameRepository.findById(testGame.getId());
        assertFalse(gameList.isPresent());
    }

    @Test
    public void updateGame(){
        testGame.setStudio("Blizzard");

        gameRepository.save(testGame);
        Optional<Game> gameList = gameRepository.findById(testGame.getId());
        assertEquals(gameList.get(), testGame);
    }

    @Test
    public void getAllGames(){
        testGame = new Game();
        testGame.setTitle("Neighbours from Heaven");
        testGame.setEsrbRating("E");
        testGame.setDescription("puzzle strategy game");
        testGame.setPrice(55.99);
        testGame.setStudio("JoWooD Productions");
        testGame.setQuantity(234);

        testGame = gameRepository.save(testGame);

        List<Game> gameList = gameRepository.findAll();
        assertEquals(gameList.size(), 2);
    }

    @Test
    public void getGameByTitle(){
        assertEquals(gameRepository.findByTitle("Neighbours from Hell").get().getTitle(), testGame.getTitle());
    }

    @Test
    public void getAllGamesByEsrbRating(){
        assertEquals(gameRepository.findByEsrbRating("E").size(), 1);
    }

    @Test
    public void getAllGamesByStudio(){
        assertEquals(gameRepository.findByStudio("JoWooD Productions").size(), 1);
    }

}