package com.example.bumil_backend.config;

import com.example.bumil_backend.common.exception.JwtAuthenticationException;
import com.example.bumil_backend.common.exception.JwtTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Map;

//@Component
//@RequiredArgsConstructor
//public class JwtChannelInterceptor {
//    private final TokenProvider tokenProvider;
//    private final UserDetailsService userDetailsService;
//    private static final String AUTH_HEADER = "Authorization";
//    private static final String BEARER_PREFIX = "Bearer ";
//
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//
//        // CONNECT 요청 처리
//        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//            String token = extractToken(accessor);
//            if (token == null) {
//                token = (String) accessor.getSessionAttributes().get("token");
//            } if (token == null) {
//                throw new JwtTokenNotFoundException("Access Token이 없습니다.");
//            } if (token == null || !tokenProvider.validateToken(token)) {
//                throw new JwtAuthenticationException("유효하지 않은 Access Token입니다.");
//            }
//
//            String username = tokenProvider.extractUsername(token);
//            if (username == null || username.isEmpty()) {
//                throw new JwtAuthenticationException("이메일을 추출할 수 없습니다.");
//            }
//
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//        }
//    }
//
//
//    private String extractToken(StompHeaderAccessor accessor) {
//        String authHeader = accessor.getFirstNativeHeader(AUTH_HEADER);
//        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
//            return authHeader.substring(BEARER_PREFIX.length());
//        }
//        return null;
//
//    }
//}



//private final JwtTokenProvider jwtTokenProvider;
//private final UserRepository userRepository;
//private final CustomSecurityUserDetails customSecurityUserDetails;
//
//private static final String AUTH_HEADER = "Authorization";
//private static final String BEARER_PREFIX = "Bearer ";
//
//@Override
//public Message<?> preSend(Message<?> message, MessageChannel channel) {
//    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//
//    // CONNECT 요청 처리
//    if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//        String token = extractToken(accessor);
//        if (token == null) {
//            token = (String) accessor.getSessionAttributes().get("token");
//        }
//
//        if (token == null || !jwtTokenProvider.validateToken(token)) {
//            throw new InvalidJwtAuthenticationException("JWT 토큰이 없거나 유효하지 않습니다.");
//        }
//
//        String username = jwtTokenProvider.getUsername(token);
//        UserDetails userDetails;
//
//        if (jwtTokenProvider.isOauthUser(token)) {
//            Users user = userRepository.findByEmailAndDeletedAtIsNull(username)
//                    .orElseThrow(() -> new UserNotFoundException("OAuth2 사용자 없음"));
//            userDetails = new CustomOauth2UserDetails(user, Map.of());
//        } else {
//            userDetails = customSecurityUserDetails.loadUserByUsername(username);
//        }
//
//        Users user = userRepository.findByEmailAndDeletedAtIsNull(username)
//                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//        accessor.setUser(authenticationToken);
//
//        // send에서 인증될 수 있도록 세션에 저장
//        accessor.getSessionAttributes().put("userPrincipal", authenticationToken);
//
//        // 세션에 사용자 정보 저장
//        accessor.getSessionAttributes().put("user", user);
//        String nickname = user.getProfile() != null
//                ? user.getProfile().getNickname()
//                : null;
//        accessor.getSessionAttributes().put("nickname", nickname);
//    }
//
//    if (StompCommand.SEND.equals(accessor.getCommand())) {
//
//        // principal 이 null 이면 세션에서 복구
//        if (accessor.getUser() == null) {
//            Object saved = accessor.getSessionAttributes().get("userPrincipal");
//
//            if (saved instanceof Principal savedUser) {
//                accessor.setUser(savedUser);
//            }
//        }
//        if (accessor.getUser() == null) {
//            throw new AccessDeniedException("STOMP SEND 인증 실패");
//        }
//    }
//
//    return message;
//}
//
//private String extractToken(StompHeaderAccessor accessor) {
//    String authHeader = accessor.getFirstNativeHeader(AUTH_HEADER);
//    if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
//        return authHeader.substring(BEARER_PREFIX.length());
//    }
//    return null;
//}