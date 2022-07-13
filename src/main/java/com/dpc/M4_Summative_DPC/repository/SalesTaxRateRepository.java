package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.models.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// <Pass in String state; not int id>
@Repository
public interface SalesTaxRateRepository extends JpaRepository<SalesTaxRate, String> {
    SalesTaxRate findByState(String state);
}
