package com.example.bumil_backend.dto.user.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserUpdateResponse {
    private Long userId;
    private String email;
    private String name;
    private LocalDateTime createdAt;
}
