package com.interbank.demo.antifraudservice.exception;

public class TransactionTypeNotFoundException extends RuntimeException {
    public TransactionTypeNotFoundException(String message) {
        super(message);
    }
}

