package com.dpc.M4_Summative_DPC.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "processing_fee", indexes = {@Index(name = "ix_product_type_fee", columnList="product_type")})
public class ProcessingFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_type")
    private String productType;

    private double fee;

    public ProcessingFee() {
    }

    public ProcessingFee(String productType, double fee) {
        this.productType = productType;
        this.fee = fee;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee that = (ProcessingFee) o;
        return Double.compare(that.fee, fee) == 0 && Objects.equals(productType, that.productType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productType, fee);
    }

    @Override
    public String toString() {
        return "ProcessingFee{" +
                "productType='" + productType + '\'' +
                ", fee=" + fee +
                '}';
    }
}
