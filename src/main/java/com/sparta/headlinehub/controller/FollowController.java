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

    @PostMapping("/following")
    public PostFollowingSaveResponseDto saveFollowing(
            @Auth AuthUser authUser,
            @RequestBody PostFollowingSaveRequestDto requestDto
    ) {
        return service.saveFollowing(authUser, requestDto);
    }
}
