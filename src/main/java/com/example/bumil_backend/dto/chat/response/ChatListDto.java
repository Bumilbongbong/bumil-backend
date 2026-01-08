package com.example.bumil_backend.dto.chat.response;

import com.example.bumil_backend.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ChatListDto {
    private Long chatRoomId;
    private String title;
    private String author;
    private LocalDateTime createdAt;

    public static ChatListDto from(ChatRoom chatRoom) {
        return ChatListDto.builder()
                .chatRoomId(chatRoom.getId())
                .title(chatRoom.getTitle())
                .author(chatRoom.getAuthor().getName())
                .createdAt(chatRoom.getCreatedAt())
                .build();
    }
}
