package com.dpc.M4_Summative_DPC.viewmodel;

import java.util.Objects;

public class ConsoleViewModel {
        private  Integer consoleId;
        private String model;
        private String manufacturer;

        private Double memoryAmount;
        private String processor;
        private Double price;
        private Integer quantity;

        public Integer getConsoleId() {
            return consoleId;
        }

        public void setConsoleId(Integer consoleId) {
            this.consoleId = consoleId;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public Double getMemoryAmount() {
            return memoryAmount;
        }

        public void setMemoryAmount(Double memoryAmount) {
            this.memoryAmount = memoryAmount;
        }

        public String getProcessor() {
            return processor;
        }

        public void setProcessor(String processor) {
            this.processor = processor;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleViewModel that = (ConsoleViewModel) o;
        return Objects.equals(consoleId, that.consoleId) && Objects.equals(model, that.model) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(memoryAmount, that.memoryAmount) && Objects.equals(processor, that.processor) && Objects.equals(price, that.price) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consoleId, model, manufacturer, memoryAmount, processor, price, quantity);
    }

    @Override
        public String toString() {
            return "Console{" +
                    "consoleId=" + consoleId +
                    ", model='" + model + '\'' +
                    ", manufacturer='" + manufacturer + '\'' +
                    ", memoryAmount=" + memoryAmount +
                    ", processor='" + processor + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    '}';
        }
    }


