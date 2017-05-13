package com.epam.receipt.model;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

public class RequestItem {

    @NotNull(message = "receipt item id can't be null")
    private Integer id;

    @NotNull(message = "receipt item name can't be null")
    private String name;

    @NotNull(message = "receipt item quantity can't be null")
    private Integer quantity;

    @NotNull(message = "receipt item price can't be null")
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
