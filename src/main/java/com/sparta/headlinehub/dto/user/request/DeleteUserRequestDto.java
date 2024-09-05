package com.sparta.headlinehub.dto.user.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class DeleteUserRequestDto {
    private String pw;
}
