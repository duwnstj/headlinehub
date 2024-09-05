package com.sparta.headlinehub.service;

import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.board.request.PostSaveRequestDto;
import com.sparta.headlinehub.dto.board.request.PostUpdateRequestDto;
import com.sparta.headlinehub.dto.board.response.GetDetailResponseDto;
import com.sparta.headlinehub.dto.board.response.PostSaveResponseDto;
import com.sparta.headlinehub.dto.board.response.PostUpdateResponseDto;
import com.sparta.headlinehub.dto.comment.response.GetCommentListResponseDto;
import com.sparta.headlinehub.entity.Board;
import com.sparta.headlinehub.entity.Follow;
import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.exception.board.AccessDeniedException;
import com.sparta.headlinehub.exception.board.ResourceNotFoundException;
import com.sparta.headlinehub.exception.user.UserNotFindException;
import com.sparta.headlinehub.repository.BoardRepository;
import com.sparta.headlinehub.repository.FollowRepository;
import com.sparta.headlinehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    // 게시물 저장
    @Transactional
    public PostSaveResponseDto saveBoard(AuthUser authUser, PostSaveRequestDto requestDto) {

        Long userId = authUser.getId();
        User user = findUser(userId);
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


    //유저 게시물 전체 조회
    public Page<GetDetailResponseDto> getUsersBoards(int page, int size, AuthUser authuser) {
        Pageable pageable = PageRequest.of(page - 1, size);
        User user = findUser(authuser.getId());
        Long userId = user.getId();

        List<Follow> followList = followRepository.findByFollowingId(userId);

        List<Long > userIds = followList.stream().map(follow -> follow.getFollower().getId()).collect(Collectors.toList());
        userIds.add(userId);

        Page<Board> boards = boardRepository.findByUserIdInOrderByCreationDateDesc(userIds, pageable);
        return boards.map(newboard -> new GetDetailResponseDto(
                newboard.getUser().getUserName(),
                newboard.getContent(),
                newboard.getTitle(),
                newboard.getCreationDate(),
                newboard.getModifiedDate(),
                newboard.getComments().stream().map(GetCommentListResponseDto::new).toList())
        );
    }

    //게시물 수정
    @Transactional
    public PostUpdateResponseDto updateboard(Long boardId, AuthUser authUser, PostUpdateRequestDto requestDto) {
        Board board = findboard(boardId);
        Long boardUserId = board.getUser().getId();
        Long userId = authUser.getId();
        right(boardUserId, userId, "게시물 수정 권한이 없습니다.");
        board.update(
                requestDto.getTitle(),
                requestDto.getContent()
        );
        return new PostUpdateResponseDto(
                board.getTitle(),
                board.getContent()
        );
    }

    //게시물 삭제
    @Transactional
    public void deleteBoard(Long boardId, AuthUser authUser) {
        Long userId = authUser.getId();
        Board board = findboard(boardId);
        Long boardUserId = board.getUser().getId();
        right(boardUserId, userId, "게시물 삭제 권한이 없습니다.");
        boardRepository.deleteById(boardId);
    }

    // userId에 해당하는 유저 객체 찾기
    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFindException("user를 찾을 수 없습니다."));
    }

    // boardId에 해당하는 게시물 객체 찾기
    private Board findboard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("게시물을 찾을 수 없습니다."));
    }

    // 게시물 작성한 사람인지 검증하는 메서드
    private void right(Long boardUserId, Long userId, String msg) {
        if (!Objects.equals(boardUserId, userId)) {
            throw new AccessDeniedException(msg);
        }
    }
}
