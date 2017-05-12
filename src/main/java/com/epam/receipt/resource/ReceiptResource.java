package com.epam.receipt.resource;

import com.epam.receipt.model.ReceiptItems;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ReceiptResource {

    @RequestMapping(path = "/receipt", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = POST)
    public Object receiptDetails(@Valid @RequestBody ReceiptItems receiptItems, BindingResult result) {
        if (result.hasFieldErrors()) {
           // throw exception
        }

        return receiptItems;
    }
}

