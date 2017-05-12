package com.epam.receipt.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Item {

    @NotNull(message = "receipt item id can't be null")
    private Integer id;

    @NotNull(message = "receipt item name can't be null")
    private String name;

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
}
