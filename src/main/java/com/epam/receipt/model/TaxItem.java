package com.epam.receipt.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({ "name", "quantity", "price" })
public class TaxItem {

    private final String name;
    private final Integer quantity;
    private final BigDecimal price;

    private TaxItem(String name, Integer quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static class Builder {

        private String name;
        private Integer quantity;
        private BigDecimal price;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public TaxItem build() {
            return new TaxItem(name, quantity, price);
        }
    }
}
