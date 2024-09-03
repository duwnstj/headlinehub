package com.sparta.headlinehub.exception;

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
}
