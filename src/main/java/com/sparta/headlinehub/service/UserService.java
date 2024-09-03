package com.sparta.headlinehub.service;

import com.sparta.headlinehub.config.JwtUtil;
import com.sparta.headlinehub.config.PasswordEncoder;
import com.sparta.headlinehub.dto.user.request.DeleteUserRequestDto;
import com.sparta.headlinehub.dto.user.request.PostUserLoginRequestDto;
import com.sparta.headlinehub.dto.user.request.PostUserSaveRequestDto;
import com.sparta.headlinehub.dto.user.response.PostUserLoginResponseDto;
import com.sparta.headlinehub.dto.user.response.PostUserSaveResponseDto;
import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encode;
    private final JwtUtil jwtUtil;

    /* 회원 가입 */
    public PostUserSaveResponseDto saveUser(PostUserSaveRequestDto requestDto) {

        // 이메일 중복 확인
        boolean overlap = repository.existsByEmail(requestDto.getEmail());
        if (overlap) {
            throw new IllegalArgumentException("이미 사용중인 ID 입니다.");
        }


        // 비밀번호 암호화
        String pw = encode.encode(requestDto.getPw());

        User user = new User(requestDto, pw);

        User saveUser = repository.save(user);

        PostUserSaveResponseDto responseDto = new PostUserSaveResponseDto(saveUser);

        return responseDto;
    }

    /* 로그인 */
    public PostUserLoginResponseDto loginUser(PostUserLoginRequestDto requestDto) {
        // 로그인 입력값 가져오기
        String userEmail = requestDto.getEmail();
        String userPw = requestDto.getPw();

        // 유저 이메일 가져오기
        User user = repository.findByEmail(userEmail).orElseThrow(
                () -> new NoSuchElementException("유저가 없습니다."));

        // 비밀번호 확인
        checkPw(userPw, user.getPw());

        // 토큰 부여
        String token = jwtUtil.createToken(user.getId(), user.getEmail());

        PostUserLoginResponseDto responseDto = new PostUserLoginResponseDto(user.getEmail(), token);

        return responseDto;
    }

    /* 회원 탈퇴 */
    public Long deleteUser(Long id, DeleteUserRequestDto requestDto) {
        // 아이디 확인
        User user = findIdUser(id);
        // 비밀번호 확인
        String userPw = requestDto.getPw();
        checkPw(userPw, user.getPw());

        repository.delete(user);

        return id;
    }

    /* 유저 고유 번호 찾기 */
    private User findIdUser(Long id) {
        return repository.findByIdOrElseThrow(id);
    }

    /* 비밀번호 확인 */
    private void checkPw(String userPw, String enPw) {
        if (!encode.matches(userPw, enPw)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
