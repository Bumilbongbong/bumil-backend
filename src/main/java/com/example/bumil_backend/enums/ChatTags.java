package com.example.bumil_backend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatTags {
    IN_PROGRESS("진행중"),
    ADOPT("채택됨"),
    REJECT("반려됨"),
    END("종료됨");

    private final String description;

}
