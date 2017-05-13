package com.epam.receipt.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"items", "sales taxes", "total"})
public class TaxReceipt {

    private List<TaxItem> items;
    @JsonProperty("sales taxes")
    private BigDecimal salesTaxes;
    private BigDecimal total;

    private TaxReceipt(List<TaxItem> items, BigDecimal salesTaxes, BigDecimal total) {
        this.items = items;
        this.salesTaxes = salesTaxes;
        this.total = total;
    }

    public List<TaxItem> getItems() {
        return items;
    }

    public BigDecimal getSalesTaxes() {
        return salesTaxes;
    }

    public BigDecimal getTotal() {
        return total;
    }


    public static class Builder {
        private List<TaxItem> items;
        private BigDecimal salesTaxes;
        private BigDecimal total;

        public Builder withItems(List<TaxItem> items) {
            this.items = items;
            return this;
        }

        public Builder withSalesTaxes(BigDecimal salesTaxes) {
            this.salesTaxes = salesTaxes;
            return this;
        }

        public Builder withTotal(BigDecimal total) {
            this.total = total;
            return this;
        }

        public TaxReceipt build() {
            return new TaxReceipt(items, salesTaxes, total);
        }
    }
}
