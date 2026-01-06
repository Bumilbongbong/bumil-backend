package com.example.bumil_backend.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteUserRequest {
    @NotNull
    private String refreshToken;
}
