package com.sparta.headlinehub.dto.profile.request;

import lombok.Getter;

@Getter
public class PutProfileUpdateRequestDto {

    private String pw; //유저에게 비밀번호를 바꿀때 현재와 같은지 비교하기 위한 기존인증 비밀번호 입력
    private String updatePw; //바꿀 비밀번호 입력

}
