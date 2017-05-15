package com.epam.receipt.dto;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class ItemCategory {

    @Id
    private Integer id;
    private String name;
    private BigDecimal taxPercentage;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }
}
