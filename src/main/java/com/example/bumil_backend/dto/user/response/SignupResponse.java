package com.example.bumil_backend.dto.user.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResponse {
    private Long userId;
}
