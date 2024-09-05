package com.sparta.headlinehub.service;

import com.sparta.headlinehub.dto.AuthUser;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    //댓글 생성
    @Transactional
    public PostSaveCommentResponseDto saveComment(
            Long boardId,
            AuthUser authUser,
            PostSaveCommentRequestDto requestDto) {

        Long userId = authUser.getId();

        User user = findUser(userId);   //토근에있는 아이디 값과 일치하는 user객체를 저장한다.
        Board board = findboard(boardId); //PathVariable 에서 boardId를 가져와서 일치하는 board객체를 찾아 저장한다.


        Comment comment = new Comment( // comment객체를 생성하면서 값을 넣어준다.
                user,
                requestDto.getComment(),
                board
        );
        commentRepository.save(comment); // 데이터 베이스에 comment를 저장한다.
        return new PostSaveCommentResponseDto( //출력값을 저장후 반환한다.
                comment.getComment()
        );

    }
//    public List<GetCommentListResponseDto> getComment(Long boardId) {
//        Board board = findboard(boardId); //PathVariable 에서 boardId를 가져와서 일치하는 board객체를 찾아 저장한다.
//
//    }



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
