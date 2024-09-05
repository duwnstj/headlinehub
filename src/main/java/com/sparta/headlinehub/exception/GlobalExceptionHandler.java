package com.sparta.headlinehub.exception;

import com.sparta.headlinehub.exception.board.AccessDeniedException;
import com.sparta.headlinehub.exception.board.BoardNotFoundException;
import com.sparta.headlinehub.exception.comment.RightDeleteCommentException;
import com.sparta.headlinehub.exception.follow.DuplicateFollowingException;
import com.sparta.headlinehub.exception.follow.WrongFollowingException;
import com.sparta.headlinehub.exception.profile.SamePasswordException;
import com.sparta.headlinehub.exception.user.DuplicateEmailException;
import com.sparta.headlinehub.exception.user.MismatchPasswordException;
import com.sparta.headlinehub.exception.user.UserNotFindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /* 유저를 찾지 못했을 때, 스스로 팔로잉을 했을때, 같은 비밀번호로 변경, 게시물을 찾지 못했을 때 (상태코드 400) */
    @ExceptionHandler({
            UserNotFindException.class,
            WrongFollowingException.class,
            SamePasswordException.class,
            BoardNotFoundException.class
    })
    public ResponseEntity<String> handlerBadRequestException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /* 비밀번호 불일치 (상태코드 401) */
    @ExceptionHandler(MismatchPasswordException.class)
    public ResponseEntity<String> handlerMismatchPasswordException(MismatchPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /* 이메일 중복, 팔로잉 중복 (상태코드 409) */
    @ExceptionHandler({
            DuplicateEmailException.class,
            DuplicateFollowingException.class
    })
    public ResponseEntity<String> handlerDuplicateIdException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /* 댓글 삭제 권한 없음, 권한이 없을 경우 (상태코드 403) */
    @ExceptionHandler({
            RightDeleteCommentException.class,
            AccessDeniedException.class
    })
    public ResponseEntity<String> handlerRightDeleteCommentException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
}
