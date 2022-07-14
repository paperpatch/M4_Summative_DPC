package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.models.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesTaxRateRepository extends JpaRepository<SalesTaxRate, Integer> {
    SalesTaxRate findByState(String state);
}
