package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.annotation.Auth;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.profile.request.PutProfileUpdateRequestDto;
import com.sparta.headlinehub.dto.profile.response.GetProfileDetailResponseDto;
import com.sparta.headlinehub.dto.profile.response.PutProfileUpdateResponseDto;
import com.sparta.headlinehub.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    /**
     * 내 프로필 조회
     * @param authUser 유저 ID, 유저 이메일
     * @return 작성한 게시물, 유저 이름, 유저 이메일, 전화번호
     */
    @GetMapping
    public ResponseEntity<GetProfileDetailResponseDto> getProfile(@Auth AuthUser authUser){
        return ResponseEntity.ok(profileService.getDetailProfile(authUser));
    }

    /**
     * 비밀번호 변경
     * @param authUser 유저 ID, 유저 이메일
     * @param requestDto 기존 비밀번호, 유효성 검사를 통과한 바꿀 비밀번호
     * @return 유저 이름
     */
    @PutMapping("/changePassword")
    public ResponseEntity<PutProfileUpdateResponseDto> updateProfile(@Auth AuthUser authUser, @Valid @RequestBody PutProfileUpdateRequestDto requestDto){
        return ResponseEntity.ok(profileService.updatePassword(authUser,requestDto));
    }

}
