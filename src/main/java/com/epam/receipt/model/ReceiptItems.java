package com.epam.receipt.model;

import javax.validation.Valid;
import java.util.List;

public class ReceiptItems {

    @Valid
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }
}
