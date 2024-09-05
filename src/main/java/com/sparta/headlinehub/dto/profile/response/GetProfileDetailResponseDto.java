package com.sparta.headlinehub.dto.profile.response;

import com.sparta.headlinehub.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class GetProfileDetailResponseDto {

    private final List<GetProfileSimpleResponseDto> posts;
    private final String userName;
    private final String email;
    private final String phoneNumber;


    public GetProfileDetailResponseDto(User user, List<GetProfileSimpleResponseDto> boardsList) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.posts = boardsList;
    }
}
