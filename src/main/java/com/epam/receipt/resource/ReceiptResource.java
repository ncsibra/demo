package com.epam.receipt.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.receipt.exception.InvalidRequestException;
import com.epam.receipt.model.ReceiptRequest;
import com.epam.receipt.model.TaxReceipt;
import com.epam.receipt.service.ReceiptService;

@RestController
public class ReceiptResource {

    private final ReceiptService service;

    @Autowired
    public ReceiptResource(ReceiptService service) {
        this.service = service;
    }

    @RequestMapping(path = "/receipt", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, method = POST)
    public TaxReceipt createReceipt(@Valid @RequestBody ReceiptRequest request, BindingResult result) {
        if (result.hasFieldErrors()) {
            throw new InvalidRequestException(result);
        }

        return service.createTaxReceipt(request.getItems());
    }
}

