package com.epam.receipt.dto;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

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
