package com.example.bumil_backend.common.exception;

public class NotLoggedInException extends RuntimeException {
    public NotLoggedInException(String message) {
        super(message);
    }
}
