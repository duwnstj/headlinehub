package com.sparta.headlinehub.service;

import com.sparta.headlinehub.dto.board.request.PostSaveRequestDto;
import com.sparta.headlinehub.dto.board.response.PostSaveResponseDto;
import com.sparta.headlinehub.entity.Board;
import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.repository.BoardRepository;
import com.sparta.headlinehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 게시물 저장
    @Transactional
    public PostSaveResponseDto saveBoard(PostSaveRequestDto requestDto) {

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()->new NullPointerException("user를 찾을 수 없습니다."));
        Board board = new Board(
                requestDto.getTitle(),
                requestDto.getContent(),
                user
        );
        Board saveBoard = boardRepository.save(board);

        return new PostSaveResponseDto(
                saveBoard.getTitle()
        );

    }
}
