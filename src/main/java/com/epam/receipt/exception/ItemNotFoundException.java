package com.epam.receipt.exception;

import com.epam.receipt.model.RequestItem;

public class ItemNotFoundException extends RuntimeException {

    private final RequestItem item;

    public ItemNotFoundException(RequestItem item) {
        this.item = item;
    }

    public RequestItem getItem() {
        return item;
    }
}
