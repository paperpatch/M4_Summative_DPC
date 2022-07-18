package com.dpc.M4_Summative_DPC.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "sales_tax_rate")
public class SalesTaxRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tax_id")
    private String state;
    private double rate;
    public SalesTaxRate() {
    }

    public SalesTaxRate(String state, double rate) {
        this.state = state;
        this.rate = rate;
    }

    public SalesTaxRate(int id, String state, double rate) {
        this.id = id;
        this.state = state;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == that.id && Double.compare(that.rate, rate) == 0 && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state, rate);
    }

    @Override
    public String toString() {
        return "SalesTaxRate{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }
}
