package com.sparta.headlinehub.service;

import com.sparta.headlinehub.config.PasswordEncoder;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.board.response.PostSaveResponseDto;
import com.sparta.headlinehub.dto.comment.request.PostSaveCommentRequestDto;
import com.sparta.headlinehub.dto.comment.response.PostSaveCommentResponseDto;
import com.sparta.headlinehub.entity.Board;
import com.sparta.headlinehub.entity.Comment;
import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.exception.ResourceNotFoundException;
import com.sparta.headlinehub.exception.user.UserNotFindException;
import com.sparta.headlinehub.repository.BoardRepository;
import com.sparta.headlinehub.repository.CommentRepository;
import com.sparta.headlinehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    //댓글 생성
    public PostSaveCommentResponseDto saveComment(Long boardId, AuthUser authUser, PostSaveCommentRequestDto requestDto) {
        Long userId = authUser.getId();
        User user = findUser(userId);

        Board board = findboard(boardId);
        Comment comment = new Comment(
                user,
                requestDto.getComment(),
                board
        );
        Comment saveComment = commentRepository.save(comment);

        return new PostSaveCommentResponseDto(
                saveComment.getComment()
        );

    }



    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFindException("유저를 찾을 수 없습니다.")
        );
    }

    private Board findboard(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("게시물을 찾을 수 없습니다."));
    }

}
