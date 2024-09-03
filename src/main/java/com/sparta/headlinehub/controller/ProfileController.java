package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.dto.profile.request.PutProfileUpdateRequestDto;
import com.sparta.headlinehub.dto.profile.response.GetProfileDetailResponseDto;
import com.sparta.headlinehub.dto.profile.response.PutProfileUpdateResponseDto;
import com.sparta.headlinehub.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;


    @GetMapping("/{userId}")
    public GetProfileDetailResponseDto getProfile(@PathVariable Long userId){
        return profileService.getDetailProfile(userId);
    }

    @PutMapping("/{userId}/changePassword")
    public PutProfileUpdateResponseDto updateProfile(@PathVariable Long userId ,@RequestBody PutProfileUpdateRequestDto requestDto){
        return profileService.updatePassword(userId,requestDto);
    }

}
