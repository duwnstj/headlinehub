package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.annotation.Auth;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.user.request.DeleteUserRequestDto;
import com.sparta.headlinehub.dto.user.request.PostUserLoginRequestDto;
import com.sparta.headlinehub.dto.user.request.PostUserSaveRequestDto;
import com.sparta.headlinehub.dto.user.response.PostUserLoginResponseDto;
import com.sparta.headlinehub.dto.user.response.PostUserSaveResponseDto;
import com.sparta.headlinehub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    /**
     * 회원 가입
     * @param requestDto 유저 이메일, 유저 비밀번호, 유저 이름, 유저 전화번호
     * @return 가입한 유저 이름
     */
    @PostMapping()
    public ResponseEntity<PostUserSaveResponseDto> saveUser(@Valid @RequestBody PostUserSaveRequestDto requestDto) {

       return  ResponseEntity.ok(service.saveUser(requestDto));
    }

    /**
     * 로그인
     * @param requestDto 유저 이메일, 유저 비밀번호
     * @return 로그인 한 유저 이메일, JWT 토큰 값
     */
    @PostMapping("/logins")
    public ResponseEntity<PostUserLoginResponseDto> loginUser(@RequestBody PostUserLoginRequestDto requestDto) {
        return ResponseEntity.ok(service.loginUser(requestDto));
    }

    /**
     * 회원 탈퇴
     * @param authUser 유저 아이디, 유저 이메일
     * @param requestDto 유저 비밀번호
     * @return 회원 탈퇴한 유저 ID
     */
    @DeleteMapping("/removes")
    public ResponseEntity<Long> deleteUser(@Auth AuthUser authUser, @RequestBody DeleteUserRequestDto requestDto) {
        return ResponseEntity.ok(service.deleteUser(authUser, requestDto));
    }
}
