package com.sparta.headlinehub.dto.follow.response;

import com.sparta.headlinehub.entity.Follow;
import lombok.Getter;

@Getter
public class GetFollowingSimpleResponseDto {
    private Long id;
    private String name;

    public GetFollowingSimpleResponseDto(Follow follow) {
        this.id = follow.getFollower().getId();
        this.name = follow.getFollower().getUserName();
    }
}
