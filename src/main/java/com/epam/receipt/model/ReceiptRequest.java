package com.epam.receipt.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ReceiptRequest {

    @Valid
    @NotNull
    @Size(min = 1)
    private List<RequestItem> items;

    public List<RequestItem> getItems() {
        return items;
    }
}
