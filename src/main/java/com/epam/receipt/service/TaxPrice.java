package com.epam.receipt.service;

import java.math.BigDecimal;

public class TaxPrice {

    private BigDecimal taxAmount;
    private BigDecimal priceAmount;

    private TaxPrice(BigDecimal taxAmount, BigDecimal priceAmount) {
        this.taxAmount = taxAmount;
        this.priceAmount = priceAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }


    public static class Builder {
        private BigDecimal taxAmount;
        private BigDecimal priceAmount;

        public Builder withTaxAmont(BigDecimal taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public Builder withPriceAmount(BigDecimal priceAmount) {
            this.priceAmount = priceAmount;
            return this;
        }

        public TaxPrice build() {
            return new TaxPrice(taxAmount, priceAmount);
        }
    }
}
