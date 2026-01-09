package com.example.bumil_backend.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequest {

    @NotBlank
    @Pattern(regexp = "\\d{4}", message = "학번은 4자리 숫자여야 합니다.")
    private Integer studentNum;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
