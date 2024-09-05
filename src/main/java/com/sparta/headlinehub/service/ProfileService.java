package com.sparta.headlinehub.service;

import com.sparta.headlinehub.config.PasswordEncoder;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.profile.request.PutProfileUpdateRequestDto;
import com.sparta.headlinehub.dto.profile.response.GetProfileDetailResponseDto;
import com.sparta.headlinehub.dto.profile.response.GetProfileSimpleResponseDto;
import com.sparta.headlinehub.dto.profile.response.PutProfileUpdateResponseDto;
import com.sparta.headlinehub.entity.Board;
import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.exception.profile.SamePasswordException;
import com.sparta.headlinehub.exception.user.MismatchPasswordException;
import com.sparta.headlinehub.exception.user.UserNotFindException;
import com.sparta.headlinehub.repository.BoardRepository;
import com.sparta.headlinehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final PasswordEncoder encode;

    public GetProfileDetailResponseDto getDetailProfile(AuthUser authUser) {
        Long userId = authUser.getId();
        User user = findUser(userId);
        List<Board> boardsList = boardRepository.getBoardIdsByUserId(userId);
        List<GetProfileSimpleResponseDto> dtoList = boardsList.stream().
                map(dto -> new GetProfileSimpleResponseDto(
                        dto.getTitle(),
                        dto.getContent(),
                        dto.getCreationDate(),
                        dto.getModifiedDate())).toList();
        return new GetProfileDetailResponseDto(user,dtoList);
    }

    //패스워드 수정
    @Transactional
    public PutProfileUpdateResponseDto updatePassword(AuthUser authUser, PutProfileUpdateRequestDto requestDto) {

        Long userId = authUser.getId();
        User user = findUser(userId);

        if(!encode.matches(requestDto.getPw(),user.getPw())){
            throw new MismatchPasswordException("비밀번호가 잘못되었습니다.");
        }

        if(requestDto.getPw().equals(requestDto.getUpdatePw())){
            throw new SamePasswordException("현재 비밀번호와 같은 비밀번호 입니다.");
        }
        String pw = encode.encode(requestDto.getUpdatePw());
        user.update(pw);

        return new PutProfileUpdateResponseDto(user.getUserName());
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFindException("유저를 찾을 수 없습니다.")
        );
    }
}
