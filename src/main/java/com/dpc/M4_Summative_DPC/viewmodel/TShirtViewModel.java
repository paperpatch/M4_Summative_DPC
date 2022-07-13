package com.dpc.M4_Summative_DPC.viewmodel;


import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class TShirtViewModel {
    private int id;
    @NotEmpty(message = "You must enter a size")
    private String size;
    @NotEmpty(message = "You must enter a color")
    private String color;
    @NotEmpty(message = "You must enter a description")
    private String description;
    @NotEmpty(message = "You must enter a price")
    private double price;
    @NotEmpty(message = "You must enter quantity")
    private int quantity;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirtViewModel that = (TShirtViewModel) o;
        return id == that.id && Double.compare(that.price, price) == 0 && quantity == that.quantity && Objects.equals(size, that.size) && Objects.equals(color, that.color) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, color, description, price, quantity);
    }
}
