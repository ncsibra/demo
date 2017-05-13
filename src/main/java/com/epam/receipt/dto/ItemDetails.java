package com.epam.receipt.dto;

import org.springframework.data.annotation.Id;

public class ItemDetails {

    @Id
    private Integer id;
    private String name;
    private ItemCategory category;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemCategory getCategory() {
        return category;
    }
}
