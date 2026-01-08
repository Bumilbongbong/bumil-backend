package com.example.bumil_backend.security;

import com.example.bumil_backend.common.exception.AccessDeniedUserException;
import com.example.bumil_backend.common.exception.NotLoggedInException;
import com.example.bumil_backend.entity.Users;

import com.example.bumil_backend.enums.Role;
import com.example.bumil_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final UserRepository userRepository;

    public Users getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotLoggedInException("로그인이 필요합니다.");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();

            return userRepository.findByEmailAndIsDeletedFalse(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User doesn't exist"));
        }

        throw new NotLoggedInException("로그인이 필요합니다.");
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public Users getCurrentAdmin() {
        Users user = getCurrentUser();
        if (user.getRole() != Role.ADMIN) {
            throw new AccessDeniedUserException("관리자 계정만 사용할 수 있는 기능입니다.");
        }
        return user;
    }
}

