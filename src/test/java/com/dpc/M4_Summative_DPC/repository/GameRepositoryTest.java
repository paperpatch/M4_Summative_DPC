package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.models.Game;
import com.dpc.M4_Summative_DPC.service.ServiceLayer;
import org.junit.After;
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
    ServiceLayer service;

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

        service = new ServiceLayer(gameRepository);
    }

    @Test
    public void shouldAddAndDeleteGame(){
        Optional<Game> gameList = gameRepository.findById(testGame.getId());
        assertEquals(gameList.get(), testGame);
        gameRepository.deleteById(testGame.getId());
        gameList = gameRepository.findById(testGame.getId());
        assertFalse(gameList.isPresent());
    }

    @Test
    public void shouldUpdateGame(){
        testGame.setStudio("Blizzard");
        gameRepository.save(testGame);
        Optional<Game> gameList = gameRepository.findById(testGame.getId());
        assertEquals(gameList.get(), testGame);
    }

    @Test
    public void shouldGetAllGames(){
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
    public void shouldGetGameByTitle(){
        assertEquals(gameRepository.findByTitle("Neighbours from Hell").get(0).getTitle(), testGame.getTitle());
    }

    @Test
    public void shouldGetAllGamesByEsrbRating(){
        assertEquals(gameRepository.findByEsrbRating("E").size(), 1);
    }

    @Test
    public void shouldGetAllGamesByStudio(){
        assertEquals(gameRepository.findByStudio("JoWooD Productions").size(), 1);
    }

    @After
    public void clearTests() {
        gameRepository.deleteAll();
    }

}