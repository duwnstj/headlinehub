package com.sparta.headlinehub.dto.profile.response;

import com.sparta.headlinehub.dto.board.response.GetDetailResponseDto;
import com.sparta.headlinehub.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class GetProfileDetailResponseDto {
    private final String userName;
    private final String email;
    private final String phoneNumber;
    private final List<GetDetailResponseDto> posts;


    public GetProfileDetailResponseDto(User user, List<GetDetailResponseDto> dtoList) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.posts = dtoList;
    }
}
