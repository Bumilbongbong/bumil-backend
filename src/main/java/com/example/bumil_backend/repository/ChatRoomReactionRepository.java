package com.example.bumil_backend.repository;

import com.example.bumil_backend.entity.ChatRoom;
import com.example.bumil_backend.entity.ChatRoomReaction;
import com.example.bumil_backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomReactionRepository extends JpaRepository<ChatRoomReaction, Long> {
    ChatRoomReaction findByUser(Users user);

    ChatRoomReaction findByUserAndChatRoom(Users user, ChatRoom chatRoom);
}
