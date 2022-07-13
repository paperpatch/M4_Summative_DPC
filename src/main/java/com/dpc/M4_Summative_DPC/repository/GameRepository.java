package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.model.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Games, Integer> {
    
}
