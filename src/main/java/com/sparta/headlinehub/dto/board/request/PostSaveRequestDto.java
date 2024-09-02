package com.sparta.headlinehub.dto.board.request;

import lombok.Getter;
import com.sparta.headlinehub.entity.User;

@Getter
public class PostSaveRequestDto {
    private Long userId;
    private String title;
    private String content;
}
