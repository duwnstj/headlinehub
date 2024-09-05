package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.annotation.Auth;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.follow.request.PostFollowingSaveRequestDto;
import com.sparta.headlinehub.dto.follow.response.GetFollowerSimpleResponseDto;
import com.sparta.headlinehub.dto.follow.response.GetFollowingSimpleResponseDto;
import com.sparta.headlinehub.dto.follow.response.PostFollowingSaveResponseDto;
import com.sparta.headlinehub.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PostFollowingSaveResponseDto> saveFollowing(
            @Auth AuthUser authUser,
            @RequestBody PostFollowingSaveRequestDto requestDto
    ) {
        return ResponseEntity.ok(service.saveFollowing(authUser, requestDto));
    }

    /**
     * 본인의 팔로잉 조회
     * @param authUser 로그인 한 유저 ID, 로그인 한 유저 Email
     * @return 내가 팔로우 한 유저 ID, 내가 팔로우 한 유저 Name
     */
    @GetMapping("/following-list")
    public  ResponseEntity<List<GetFollowingSimpleResponseDto>> getFollowing(@Auth AuthUser authUser) {
        return ResponseEntity.ok(service.getFollowing(authUser));
    }

    /**
     * 본인의 팔로워 조회
     * @param authUser 로그인 한 유저 ID, 로그인 한 유저 Email
     * @return 나를 팔로우 한 유저 ID, 나를 팔로우 한 유저 Name
     */
    @GetMapping("/follower-list")
    public ResponseEntity<List<GetFollowerSimpleResponseDto>> getFollower(@Auth AuthUser authUser) {
        return ResponseEntity.ok(service.getFollower(authUser));
    }

    /**
     * 언팔로우
     * @param authUser 유저 ID, email
     * @param followUserId 언팔로우 할 유저 ID
     * @return 언팔로우한 유저 ID
     */
    @DeleteMapping("/unfollow/{followUserId}")
    public ResponseEntity<Long> deleteFollow(@Auth AuthUser authUser, @PathVariable Long followUserId) {
        return ResponseEntity.ok(service.deleteFollow(authUser, followUserId));
    }
}
