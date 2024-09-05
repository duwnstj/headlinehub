package com.sparta.headlinehub.dto.profile.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class PutProfileUpdateRequestDto {

    private String pw; //유저에게 비밀번호를 바꿀때 현재와 같은지 비교하기 위한 기존인증 비밀번호 입력

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&#.~_-])[A-Za-z\\d@$!%*?&#.~_-]{8,}$",
            message = "비밀번호 형식이 올바르지 않습니다. 최소 8자 이상, 대소문자, 숫자, 특수문자(?=.*[@$!%*?&#.~_-)가 1개씩 포함되어야 합니다.")
    private String updatePw; //바꿀 비밀번호 입력

}
