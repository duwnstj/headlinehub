package com.sparta.headlinehub.dto.profile.response;

import lombok.Getter;

@Getter
public class PutProfileUpdateResponseDto {
    private final String userName;

    public PutProfileUpdateResponseDto(String userName){
        this.userName = userName;
    }
}
