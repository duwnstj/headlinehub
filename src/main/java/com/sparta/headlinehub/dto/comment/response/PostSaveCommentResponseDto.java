package com.sparta.headlinehub.dto.comment.response;

import lombok.Getter;

@Getter
public class PostSaveCommentResponseDto {
    private final String comment;
    public PostSaveCommentResponseDto(String comment) {
        this.comment = comment;
    }
}
