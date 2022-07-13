package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}
