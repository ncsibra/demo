package com.epam.receipt.model.exception;

public class Error<T> {

    private final String message;
    private final T details;

    public Error(String message, T details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public T getDetails() {
        return details;
    }
}
