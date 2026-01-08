package com.example.bumil_backend.dto.user.response;

import com.example.bumil_backend.entity.Users;
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

    public static UserUpdateResponse from(Users user) {
        return UserUpdateResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
