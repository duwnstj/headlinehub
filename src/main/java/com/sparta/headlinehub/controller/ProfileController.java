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

    @GetMapping
    public ResponseEntity<GetProfileDetailResponseDto> getProfile(@Auth AuthUser authUser){
        return ResponseEntity.ok(profileService.getDetailProfile(authUser));
    }

    @PutMapping("/changePassword")
    public ResponseEntity<PutProfileUpdateResponseDto> updateProfile(@Auth AuthUser authUser, @Valid @RequestBody PutProfileUpdateRequestDto requestDto){
        return ResponseEntity.ok(profileService.updatePassword(authUser,requestDto));
    }

}
