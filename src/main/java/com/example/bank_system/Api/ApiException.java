package com.example.bank_system.Api;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
