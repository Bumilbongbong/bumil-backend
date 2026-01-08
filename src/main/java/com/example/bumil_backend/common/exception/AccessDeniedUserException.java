package com.example.bumil_backend.common.exception;

public class AccessDeniedUserException extends RuntimeException {
    public AccessDeniedUserException(String message) {
        super(message);
    }
}
