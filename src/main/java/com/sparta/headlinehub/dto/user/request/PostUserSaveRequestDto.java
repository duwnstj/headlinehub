package com.sparta.headlinehub.dto.user.request;

import lombok.Getter;

@Getter
public class PostUserSaveRequestDto {
    private String email;
    private String pw;
    private String userName;
    private String phoneNumber;
}
