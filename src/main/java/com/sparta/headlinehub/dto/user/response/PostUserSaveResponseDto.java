package com.sparta.headlinehub.dto.user.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.headlinehub.entity.User;
import lombok.Getter;

@Getter
public class PostUserSaveResponseDto {
    private String userName;

    @JsonIgnore //json으로 반환 되지 않는다.
    private String token;

    public PostUserSaveResponseDto(User user) {
        this.userName = user.getUserName();

    }
    public PostUserSaveResponseDto(User user , String token){
        this.userName = user.getUserName();
        this.token = token;

    }
}
