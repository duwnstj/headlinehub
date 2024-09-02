package com.sparta.headlinehub.dto.profile.response;

import com.sparta.headlinehub.dto.board.response.GetDetailResponseDto;
import com.sparta.headlinehub.entity.Board;
import com.sparta.headlinehub.entity.User;
import lombok.Getter;

import java.util.List;

@Getter
public class GetProfileDetailResponseDto {
    private final String userName;
    private final String email;
    private final String phoneNumber;
    private final List<Board> posts;


    public GetProfileDetailResponseDto(User user, List<Board> boardsList) {
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.posts = boardsList;
    }
}
