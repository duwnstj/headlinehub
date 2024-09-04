package com.sparta.headlinehub.dto;

import lombok.Getter;

@Getter
public class AuthUser {
    private final Long id;
    private final String email;

    /* 토큰 안에 값을 부여 */
    public AuthUser(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
