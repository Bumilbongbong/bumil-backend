package com.example.bumil_backend.dto.user.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignupRequest {
    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;
}
