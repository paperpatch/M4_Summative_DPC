package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.models.Console;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsoleRepository extends JpaRepository<Console, Integer> {
    List<Console> findByManufacturer(String manufacturer);

}
