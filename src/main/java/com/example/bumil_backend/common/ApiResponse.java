package com.example.bumil_backend.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean success;

    // 응답 데이터가 존재할 경우 200
    public static <T> ResponseEntity<ApiResponse<T>> ok(T data, String message){
        return ResponseEntity.status(200).body(
                ApiResponse.<T>builder()
                        .success(true)
                        .data(data)
                        .message(message)
                        .build()
        );
    }

    // 응답 데이터가 존재하지 않을 경우 200
    public static <T> ResponseEntity<ApiResponse<T>> ok(String message){
        return ResponseEntity.status(200).body(
                ApiResponse.<T>builder()
                        .success(true)
                        .message(message)
                        .build()
        );
    }

    // 에러
    public static <T> ResponseEntity<ApiResponse<T>> fail(String message, HttpStatus status){
        return ResponseEntity.status(status).body(
                ApiResponse.<T>builder()
                        .success(false)
                        .message(message)
                        .build()
        );
    }
}