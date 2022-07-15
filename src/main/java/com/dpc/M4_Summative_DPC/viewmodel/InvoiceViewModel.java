package com.dpc.M4_Summative_DPC.viewmodel;

import com.dpc.M4_Summative_DPC.models.Console;
import com.dpc.M4_Summative_DPC.models.Game;
import com.dpc.M4_Summative_DPC.models.TShirt;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class InvoiceViewModel {
    private int invoiceId;
    @NotEmpty(message = "You must enter a name")
    private String name;
    @NotEmpty(message = "You must enter a street")
    private String street;
    @NotEmpty(message = "You must enter a city")
    private String city;
    @NotEmpty(message = "You must enter a state")
    private String state;
    @NotEmpty(message = "You must enter a zipcode")
    private String zipCode;
    @Column(name= "item_type")
     private String itemType;
    @Column(name= "item_id")
    private Integer itemId;
    @Column(name= "unit_price")
    private double unitPrice;
    private int quantity;
    private double subtotal;
    private double tax;
    @Column(name= "processing_fee")
    private double processingFee;
    private double total;

    public InvoiceViewModel() {
    }

    public InvoiceViewModel(String name, String street, String city, String state, String zipCode, String itemType, Integer itemId, double unitPrice, int quantity, double subtotal, double tax, double processingFee, double total) {
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

    public InvoiceViewModel(int invoiceId, String name, String street, String city, String state, String zipCode, String itemType, Integer itemId, double unitPrice, int quantity, double subtotal, double tax, double processingFee, double total) {
        this.invoiceId = invoiceId;
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

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return invoiceId == that.invoiceId && Double.compare(that.unitPrice, unitPrice) == 0 && quantity == that.quantity && Double.compare(that.subtotal, subtotal) == 0 && Double.compare(that.tax, tax) == 0 && Double.compare(that.processingFee, processingFee) == 0 && Double.compare(that.total, total) == 0 && Objects.equals(name, that.name) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zipCode, that.zipCode) && Objects.equals(itemType, that.itemType) && Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, name, street, city, state, zipCode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "invoiceId=" + invoiceId +
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
