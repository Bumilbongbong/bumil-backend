package com.example.bumil_backend.repository;

import com.example.bumil_backend.entity.ChatMessage;
import com.example.bumil_backend.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    Page<ChatMessage> findByChatRoomAndIsDeletedFalse(
            ChatRoom chatRoom,
            Pageable pageable
    );

    List<ChatMessage> findByChatRoomAndIsDeletedFalseOrderByCreatedAtAsc(ChatRoom chatRoom);



}
