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

    /* 회원 가입 */
    public PostUserSaveResponseDto saveUser(PostUserSaveRequestDto requestDto) {
        String pw = encode.encode(requestDto.getPw());

        User user = new User(requestDto,pw);
        User saveUser = repository.save(user);

        PostUserSaveResponseDto responseDto = new PostUserSaveResponseDto(saveUser);

        return responseDto;
    }

    /* 회원 탈퇴 */
    public Long deleteUser(Long id) {
        User user = findIdUser(id);
        repository.delete(user);

        return id;
    }

    /* 유저 고유 번호 찾기 */
    private User findIdUser(Long id) {
        return repository.findByIdOrElseThrow(id);
    }
}
