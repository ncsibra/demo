package com.epam.receipt.model.exception;

public class ItemNotFoundDetail {

    private final Integer id;
    private final String name;

    public ItemNotFoundDetail(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
