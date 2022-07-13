package com.dpc.M4_Summative_DPC.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "sales_tax_rate", indexes = {@Index(name = "ix_state_rate", columnList="state")}) // https://www.baeldung.com/jpa-indexes & https://stackoverflow.com/questions/3405229/specifying-an-index-non-unique-key-using-jpa
public class SalesTaxRate {
    @Id
    @Column(name = "tax_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String state;

    private double rate;

    public SalesTaxRate() {
    }

    public SalesTaxRate(String state, double rate) {
        this.state = state;
        this.rate = rate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTaxRate that = (SalesTaxRate) o;
        return Double.compare(that.rate, rate) == 0 && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }

    @Override
    public String toString() {
        return "SalesTaxRate{" +
                "state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }
}
