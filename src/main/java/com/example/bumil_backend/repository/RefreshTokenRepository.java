package com.example.bumil_backend.repository;


import com.example.bumil_backend.dto.refreshToken.RefreshDto;
import com.example.bumil_backend.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshDto> findByToken(String token);

    void deleteByToken(String token);
}
