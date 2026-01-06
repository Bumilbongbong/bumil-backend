package com.example.bumil_backend.dto.refreshToken.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RefreshRequest {
    @NotNull
    private String refreshToken;
}
