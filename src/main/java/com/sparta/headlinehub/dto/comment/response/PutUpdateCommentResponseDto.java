package com.sparta.headlinehub.dto.comment.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PutUpdateCommentResponseDto {
    private final String comment;
}
