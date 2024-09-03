package com.sparta.headlinehub.service;

import com.sparta.headlinehub.dto.board.request.PostSaveRequestDto;
import com.sparta.headlinehub.dto.board.request.PostUpdateRequestDto;
import com.sparta.headlinehub.dto.board.response.GetDetailResponseDto;
import com.sparta.headlinehub.dto.board.response.PostSaveResponseDto;
import com.sparta.headlinehub.dto.board.response.PostUpdateResponseDto;
import com.sparta.headlinehub.dto.user.UserDto;
import com.sparta.headlinehub.entity.Board;
import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.exception.InvalidPrivilegeException;
import com.sparta.headlinehub.exception.ResourceNotFoundException;
import com.sparta.headlinehub.repository.BoardRepository;
import com.sparta.headlinehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 게시물 저장
    @Transactional
    public PostSaveResponseDto saveBoard(PostSaveRequestDto requestDto) {

        Board board = new Board(
                requestDto.getTitle(),
                requestDto.getContent()
        );
        Board saveBoard = boardRepository.save(board);

        return new PostSaveResponseDto(
                saveBoard.getTitle()
        );

    }

    public Page<GetDetailResponseDto> getUsersBoards(int page, int size, Long userId) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Board> boards = boardRepository.findByUserIdOrderByCreationDateDesc(userId, pageable);
        return boards.map(newboard -> {
            User user = newboard.getUser();
            return new GetDetailResponseDto(
                    new UserDto(user.getUserName()),
                    newboard.getContent(),
                    newboard.getTitle(),
                    newboard.getCreationDate(),
                    newboard.getModifiedDate()
            );
        });
    }

    @Transactional
    public PostUpdateResponseDto updateboard(Long boardId, Long userId, PostUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("게시물을 찾을 수 없습니다."));

        if (!Objects.equals(board.getUser().getId(), userId)) {
            throw new InvalidPrivilegeException("게시물 수정 권한이 없습니다.");
        }
        board.update(
                requestDto.getTitle(),
                requestDto.getContent()
        );

        return new PostUpdateResponseDto(
                board.getTitle(),
                board.getContent()
        );


    }
}
