package com.sparta.headlinehub.service;

import com.sparta.headlinehub.dto.board.response.GetDetailResponseDto;
import com.sparta.headlinehub.dto.profile.request.PutProfileUpdateRequestDto;
import com.sparta.headlinehub.dto.profile.response.GetProfileDetailResponseDto;
import com.sparta.headlinehub.dto.profile.response.PutProfileUpdateResponseDto;
import com.sparta.headlinehub.entity.Board;
import com.sparta.headlinehub.entity.Profile;
import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.repository.BoardRepository;
import com.sparta.headlinehub.repository.ProfileRepository;
import com.sparta.headlinehub.repository.UserRepository;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public GetProfileDetailResponseDto getDetailProfile(Long userId) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new NullPointerException("user 를 찾을 수 없습니다."));
        List<Board> boardsList = boardRepository.getBoardIdsByUserId(userId);
        List<GetDetailResponseDto> dtoList = boardsList.stream().map(GetDetailResponseDto::new).toList();
        return new GetProfileDetailResponseDto(user,dtoList);
    }

    //패스워드 수정
    public PutProfileUpdateResponseDto updatePassword(Long userId, PutProfileUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("유저가 없습니다."));

        if(user.getPw().equals(requestDto.getPw())){

        }
    }
}
