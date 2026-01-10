package com.example.bumil_backend.dto.chat.request;

import com.example.bumil_backend.enums.ReactionType;
import lombok.Getter;

@Getter
public class ChatReactionRequest {
    private Long chatRoomId;
    private ReactionType reactionType;
}
