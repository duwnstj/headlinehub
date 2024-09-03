package com.sparta.headlinehub.dto.user.response;

import lombok.Getter;

@Getter
public class PostUserLoginResponseDto {
    private String email;
    private String token;

    public PostUserLoginResponseDto(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
