package com.epam.receipt.model.exception;

public class ValidationDetail {

    private final String path;
    private final String message;

    public ValidationDetail(String path, String message) {
        this.path = path;
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }

}
