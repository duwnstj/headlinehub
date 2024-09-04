package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.annotation.Auth;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.profile.request.PutProfileUpdateRequestDto;
import com.sparta.headlinehub.dto.profile.response.GetProfileDetailResponseDto;
import com.sparta.headlinehub.dto.profile.response.PutProfileUpdateResponseDto;
import com.sparta.headlinehub.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;


    @GetMapping
    public GetProfileDetailResponseDto getProfile(@Auth AuthUser authUser){
        return profileService.getDetailProfile(authUser);
    }

    @PutMapping("/changePassword")
    public PutProfileUpdateResponseDto updateProfile(@Auth AuthUser authUser, @Valid @RequestBody PutProfileUpdateRequestDto requestDto){
        return profileService.updatePassword(authUser,requestDto);
    }

}
