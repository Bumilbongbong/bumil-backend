package com.example.bumil_backend.config;

import com.example.bumil_backend.common.exception.ChatRoomAccessDeniedException;
import com.example.bumil_backend.common.exception.ChatRoomNotFoundException;
import com.example.bumil_backend.common.exception.NotLoggedInException;
import com.example.bumil_backend.entity.ChatRoom;
import com.example.bumil_backend.entity.Users;
import com.example.bumil_backend.enums.Role;
import com.example.bumil_backend.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomStompHandler implements ChannelInterceptor {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        String destination = accessor.getDestination();

        if (StompCommand.CONNECT.equals(command)) {
            return message;
        }

        if (destination == null) {
            return message;
        }

        // 연결, 전송 검증
        if (StompCommand.SUBSCRIBE.equals(command) || StompCommand.SEND.equals(command)) {

            Long chatRoomId = extractRoomId(destination);
            Users user = (Users) accessor.getSessionAttributes().get("user");

            validateChatAccess(chatRoomId, user);
        }

        return message;
    }

    private void validateChatAccess(Long chatRoomId, Users user) {

        if (user == null) {
            throw new NotLoggedInException("로그인이 필요합니다.");
        }

        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() ->
                        new ChatRoomNotFoundException("채팅방을 찾을 수 없습니다.")
                );

        if (chatRoom.isDeleted()) {
            throw new ChatRoomNotFoundException("삭제된 채팅방입니다.");
        }

        boolean isAuthor =
                chatRoom.getAuthor().getId().equals(user.getId());

        boolean isAdmin =
                user.getRole() == Role.ADMIN;

        if (!isAuthor && !isAdmin) {
            throw new ChatRoomAccessDeniedException("해당 채팅방에 대한 권한이 없습니다.");
        }
    }


    private Long extractRoomId(String destination) {
        try {
            return Long.parseLong(
                    destination.substring(destination.lastIndexOf("/") + 1)
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 채팅방 경로입니다.");
        }
    }
}
