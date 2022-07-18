package com.dpc.M4_Summative_DPC.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "invoice_id")
    private int id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    @Column(name= "item_type")
    private String itemType;
    @Column(name= "item_id")
    private int itemId;
    @Column(name= "unit_price")
    private double unitPrice;
    private int quantity;
    private double subtotal;
    @Column(name= "tax")
    private double tax;
    @Column(name= "processing_fee")
    private double processingFee;
    private double total;

    public Invoice() {
    }

    public Invoice(String name, String street, String city, String state, String zipCode, String itemType, int itemId) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.itemType = itemType;
        this.itemId = itemId;
    }

    public Invoice(String name, String street, String city, String state, String zipCode, String itemType, int itemId, double unitPrice, int quantity, double subtotal, double tax, double processingFee, double total) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.tax = tax;
        this.processingFee = processingFee;
        this.total = total;
    }

    public Invoice(int id, String name, String street, String city, String state, String zipCode, String itemType, int itemId, double unitPrice, int quantity, double subtotal, double tax, double processingFee, double total) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.tax = tax;
        this.processingFee = processingFee;
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id && itemId == invoice.itemId && Double.compare(invoice.unitPrice, unitPrice) == 0 && quantity == invoice.quantity && Double.compare(invoice.subtotal, subtotal) == 0 && Double.compare(invoice.tax, tax) == 0 && Double.compare(invoice.processingFee, processingFee) == 0 && Double.compare(invoice.total, total) == 0 && Objects.equals(name, invoice.name) && Objects.equals(street, invoice.street) && Objects.equals(city, invoice.city) && Objects.equals(state, invoice.state) && Objects.equals(zipCode, invoice.zipCode) && Objects.equals(itemType, invoice.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, city, state, zipCode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
