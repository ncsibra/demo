package com.epam.receipt.model;

import java.util.List;
import javax.validation.Valid;

public class ReceiptRequest {

    @Valid
    private List<RequestItem> items;

    public List<RequestItem> getItems() {
        return items;
    }
}
