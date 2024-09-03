package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.dto.user.request.DeleteUserRequestDto;
import com.sparta.headlinehub.dto.user.request.PostUserLoginRequestDto;
import com.sparta.headlinehub.dto.user.request.PostUserSaveRequestDto;
import com.sparta.headlinehub.dto.user.response.PostUserLoginResponseDto;
import com.sparta.headlinehub.dto.user.response.PostUserSaveResponseDto;
import com.sparta.headlinehub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public PostUserSaveResponseDto saveUser(@Valid @RequestBody PostUserSaveRequestDto requestDto) {
        return service.saveUser(requestDto);
    }

    // 회원 로그인
    @PostMapping("/logins")
    public PostUserLoginResponseDto loginUser(@Valid @RequestBody PostUserLoginRequestDto requestDto) {
        return service.loginUser(requestDto);
    }

    /**
     * 회원 탈퇴
     * @param id 삭제할 유저 아이디값
     * @return 삭제 성공한 유저 아이디
     */
    @DeleteMapping("/{id}")
    public Long deleteUser(@PathVariable Long id, @RequestBody DeleteUserRequestDto requestDto) {
        return service.deleteUser(id, requestDto);
    }
}
