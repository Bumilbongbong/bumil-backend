package com.example.bumil_backend.controller;

import com.example.bumil_backend.common.ApiResponse;
import com.example.bumil_backend.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    @PatchMapping("/{messageId}")
    public ResponseEntity<ApiResponse<Void>> deleteMessage(
            @RequestParam Long messageId
    ){
        messageService.deleteMessage(messageId);
        return ApiResponse.ok("삭제된 메세지입니다.");
    }
}
