package com.epam.receipt.exception;

import org.springframework.validation.BindingResult;

public class InvalidRequestException extends RuntimeException {

    private final BindingResult result;

    public InvalidRequestException(BindingResult result) {
        this.result = result;
    }

    public BindingResult getResult() {
        return result;
    }
}
