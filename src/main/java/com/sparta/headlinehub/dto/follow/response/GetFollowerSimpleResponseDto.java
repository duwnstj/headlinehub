package com.sparta.headlinehub.dto.follow.response;

import com.sparta.headlinehub.entity.Follow;
import lombok.Getter;

@Getter
public class GetFollowerSimpleResponseDto {
    private Long id;
    private String name;

    public GetFollowerSimpleResponseDto(Follow follow) {
        this.id = follow.getFollowing().getId();
        this.name = follow.getFollowing().getUserName();
    }
}
