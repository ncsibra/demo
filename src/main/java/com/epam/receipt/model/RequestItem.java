package com.epam.receipt.model;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RequestItem {

    @NotNull(message = "receipt item id can't be null")
    @Range(min = 1)
    private Integer id;

    @NotNull(message = "receipt item name can't be null")
    private String name;

    @NotNull(message = "receipt item quantity can't be null")
    @Range(min = 1)
    private Integer quantity;

    @NotNull(message = "receipt item price can't be null")
    @Range(min = 0)
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
