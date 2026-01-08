package com.example.bumil_backend.common.exception;

public class JwtTokenNotFoundException extends RuntimeException {
    public JwtTokenNotFoundException(String message) {
        super(message);
    }
}
