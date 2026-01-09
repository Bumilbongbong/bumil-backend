package com.example.bumil_backend.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private Integer studentNum;
}
