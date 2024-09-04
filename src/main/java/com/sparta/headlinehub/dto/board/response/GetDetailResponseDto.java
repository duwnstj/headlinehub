package com.sparta.headlinehub.dto.board.response;

import com.sparta.headlinehub.dto.user.UserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetDetailResponseDto {


    private final UserDto user;
    private final String title;
    private final String content;
    private final LocalDateTime creationDate;
    private final LocalDateTime modifiedDate;

}
