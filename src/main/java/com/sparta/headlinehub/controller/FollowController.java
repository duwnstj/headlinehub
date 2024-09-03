package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.annotation.Auth;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.follow.request.PostFollowingSaveRequestDto;
import com.sparta.headlinehub.dto.follow.response.PostFollowingSaveResponseDto;
import com.sparta.headlinehub.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService service;

    /**
     * 팔로잉 기능
     * @param authUser 로그인 인증 토큰
     * @param requestDto 팔로잉할 유저 ID
     * @return 내 ID, 팔로우한 ID
     */
    @PostMapping("/following")
    public PostFollowingSaveResponseDto saveFollowing(
            @Auth AuthUser authUser,
            @RequestBody PostFollowingSaveRequestDto requestDto
    ) {
        return service.saveFollowing(authUser, requestDto);
    }
}
