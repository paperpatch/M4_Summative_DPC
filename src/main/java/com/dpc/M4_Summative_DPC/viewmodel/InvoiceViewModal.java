package com.dpc.M4_Summative_DPC.viewmodel;

import com.dpc.M4_Summative_DPC.models.Console;
import com.dpc.M4_Summative_DPC.models.Game;
import com.dpc.M4_Summative_DPC.models.TShirt;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class InvoiceViewModal {
    private Integer invoiceId;
    @NotEmpty(message = "You must enter a name")
    private String name;
    @NotEmpty(message = "You must enter a street")
    private String street;
    @NotEmpty(message = "You must enter a city")
    private String city;
    @NotEmpty(message = "You must enter a state")
    private String state;
    @NotEmpty(message = "You must enter a zipcode")
    private String Zipcode;
    @Column(name= "item_type")
     private String itemType;
    @Column(name= "item_id")
    private Integer itemId;
    @Column(name= "unit_price")
    private Double unitPrice;
    private Integer quantity;
    private Double subtotal;
    @Column(name= "sale_tax_rate")
    private Double saleTaxRate;
    @Column(name= "processing_fee")
    private Double processingFee;
    private Double total;
    private Game game;
    private TShirt tShirt;
    private Console console;

    public InvoiceViewModal(Integer invoiceId, String name, String street, String city, String state, String zipcode, String itemType, Integer itemId, Double unitPrice, Integer quantity, Double subtotal, Double saleTaxRate, Double processingFee, Double total, Game game, TShirt tShirt, Console console) {
        this.invoiceId = invoiceId;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        Zipcode = zipcode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.saleTaxRate = saleTaxRate;
        this.processingFee = processingFee;
        this.total = total;
        this.game = game;
        this.tShirt = tShirt;
        this.console = console;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
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

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getSaleTaxRate() {
        return saleTaxRate;
    }

    public void setSaleTaxRate(Double saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    public Double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Double processingFee) {
        this.processingFee = processingFee;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public TShirt gettShirt() {
        return tShirt;
    }

    public void settShirt(TShirt tShirt) {
        this.tShirt = tShirt;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModal that = (InvoiceViewModal) o;
        return Objects.equals(invoiceId, that.invoiceId) && Objects.equals(name, that.name) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(Zipcode, that.Zipcode) && Objects.equals(itemType, that.itemType) && Objects.equals(itemId, that.itemId) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(quantity, that.quantity) && Objects.equals(subtotal, that.subtotal) && Objects.equals(saleTaxRate, that.saleTaxRate) && Objects.equals(processingFee, that.processingFee) && Objects.equals(total, that.total) && Objects.equals(game, that.game) && Objects.equals(tShirt, that.tShirt) && Objects.equals(console, that.console);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, name, street, city, state, Zipcode, itemType, itemId, unitPrice, quantity, subtotal, saleTaxRate, processingFee, total, game, tShirt, console);
    }

    @Override
    public String toString() {
        return "InvoiceViewModal{" +
                "invoiceId=" + invoiceId +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", Zipcode='" + Zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", saleTaxRate=" + saleTaxRate +
                ", processingFee=" + processingFee +
                ", total=" + total +
                ", game=" + game +
                ", tShirt=" + tShirt +
                ", console=" + console +
                '}';
    }
}
