package com.dpc.M4_Summative_DPC.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "console_id")
    private  int consoleId;
    private String Model;
    private String manufacturer;
    @Column(name = "memory_amount")
    private double memoryAmount;
    private String processor;
    private double price;
    private int quantity;

    public Console(int consoleId, String model, String manufacturer, double memoryAmount, String processor, double price, int quantity) {
        this.consoleId = consoleId;
        Model = model;
        this.manufacturer = manufacturer;
        this.memoryAmount = memoryAmount;
        this.processor = processor;
        this.price = price;
        this.quantity = quantity;
    }

    public Console() {
    }

    public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(double memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
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
        Console console = (Console) o;
        return consoleId == console.consoleId && Double.compare(console.memoryAmount, memoryAmount) == 0 && Double.compare(console.price, price) == 0 && quantity == console.quantity && Objects.equals(Model, console.Model) && Objects.equals(manufacturer, console.manufacturer) && Objects.equals(processor, console.processor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consoleId, Model, manufacturer, memoryAmount, processor, price, quantity);
    }

    @Override
    public String toString() {
        return "Console{" +
                "consoleId=" + consoleId +
                ", Model='" + Model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memoryAmount=" + memoryAmount +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
