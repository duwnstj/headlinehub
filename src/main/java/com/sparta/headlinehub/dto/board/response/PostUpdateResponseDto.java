package com.sparta.headlinehub.dto.board.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostUpdateResponseDto {
    private final String title;
    private final String content;
}
