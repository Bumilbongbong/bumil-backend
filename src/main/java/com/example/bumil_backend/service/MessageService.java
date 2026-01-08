package com.example.bumil_backend.service;

import com.example.bumil_backend.common.exception.NotAcceptableUserException;
import com.example.bumil_backend.common.exception.ResourceNotFoundException;
import com.example.bumil_backend.entity.ChatMessage;
import com.example.bumil_backend.entity.Users;
import com.example.bumil_backend.repository.ChatMessageRepository;
import com.example.bumil_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    @Transactional
    public void deleteMessage(Long messageId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Users user = userRepository.findByEmailAndIsDeletedFalse(email)
                .orElseThrow(() -> new ResourceNotFoundException("유저를 찾을 수 없습니다."));

        ChatMessage chatMessage = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("메세지를 삭제할 수 없습니다."));

        if(chatMessage.getSender() != user){
            throw new NotAcceptableUserException("메세제 삭제 권한이 없습니다.");
        }

        chatMessage.delete();
    }
}
