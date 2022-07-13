package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByTitle(String title);
    List<Game> findByRating(String esrbRating);
    List<Game> findByStudio(String studio);
}
