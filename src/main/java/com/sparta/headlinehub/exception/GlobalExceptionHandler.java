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
    /* 유저를 찾지 못했을 때 (상태코드 400) */
    @ExceptionHandler(UserNotFindException.class)
    public ResponseEntity<String> handlerUserNotFindException(UserNotFindException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /* 비밀번호 불일치 (상태코드 401) */
    @ExceptionHandler(MismatchPasswordException.class)
    public ResponseEntity<String> handlerMismatchPasswordException(MismatchPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /* 이메일 중복 (상태코드 409) */
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handlerDuplicateIdException(DuplicateEmailException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /* 스스로를 팔로잉 (상태코드 400) */
    @ExceptionHandler(WrongFollowingException.class)
    public ResponseEntity<String> handlerWrongFollowingException(WrongFollowingException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /* 팔로잉 중복 (상태코드 409) */
    @ExceptionHandler(DuplicateFollowingException.class)
    public ResponseEntity<String> handlerDuplicateFollowingException(DuplicateFollowingException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    /* 댓글 삭제 권한 없음 (상태코드 403) */
    @ExceptionHandler(RightDeleteCommentException.class)
    public ResponseEntity<String> handlerRightDeleteCommentException(RightDeleteCommentException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    /* 같은 비밀번호로 변경(상태코드 400) */
    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<String> handlerSamePasswordException(SamePasswordException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /* 게시물을 찾지 못했을 때 */
    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<String> handlerBoardNotFoundException(BoardNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /* 권한이 없을 경우 (상태코드 403) */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handlerAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

}
