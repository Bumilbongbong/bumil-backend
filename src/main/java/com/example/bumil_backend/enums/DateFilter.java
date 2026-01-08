package com.example.bumil_backend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DateFilter {
    RECENT("최신순"), OLDEST("오래된순");
    private final String description;
}


