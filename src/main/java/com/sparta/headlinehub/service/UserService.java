package com.sparta.headlinehub.service;

import com.sparta.headlinehub.config.PasswordEncoder;
import com.sparta.headlinehub.dto.user.request.PostUserSaveRequestDto;
import com.sparta.headlinehub.dto.user.response.PostUserSaveResponseDto;
import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encode;

    public PostUserSaveResponseDto saveUser(PostUserSaveRequestDto requestDto) {
        String pw = encode.encode(requestDto.getPw());

        User user = new User(requestDto,pw);
        User saveUser = repository.save(user);

        PostUserSaveResponseDto responseDto = new PostUserSaveResponseDto(saveUser);

        return responseDto;
    }
}
