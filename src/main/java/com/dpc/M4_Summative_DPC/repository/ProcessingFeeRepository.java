package com.dpc.M4_Summative_DPC.repository;

import com.dpc.M4_Summative_DPC.models.ProcessingFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingFeeRepository extends JpaRepository<ProcessingFee, Integer> {
    ProcessingFee findByProductType(String productType);
}
