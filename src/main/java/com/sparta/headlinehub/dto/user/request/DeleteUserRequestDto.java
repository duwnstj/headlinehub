package com.sparta.headlinehub.dto.user.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class DeleteUserRequestDto {

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&#.~_-])[A-Za-z\\d@$!%*?&#.~_-]{8,}$",
            message = "비밀번호 형식이 올바르지 않습니다. 최소 8자 이상, 대소문자, 숫자, 특수문자(?=.*[@$!%*?&#.~_-)가 1개씩 포함되어야 합니다.")
    private String pw;
}
