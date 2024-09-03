package com.sparta.headlinehub.dto.follow.response;

import com.sparta.headlinehub.entity.Follow;
import lombok.Getter;

@Getter
public class PostFollowingSaveResponseDto {
    private Long my;
    private Long user;

    public PostFollowingSaveResponseDto(Follow following) {
        this.my = following.getFollowing().getId();
        this.user = following.getFollower().getId();
    }
}
