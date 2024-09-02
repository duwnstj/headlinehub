package com.sparta.headlinehub.dto.user.response;

import com.sparta.headlinehub.entity.User;
import lombok.Getter;

@Getter
public class PostUserSaveResponseDto {
    private String userName;

    public PostUserSaveResponseDto(User user) {
        this.userName = user.getUserName();
    }
}
