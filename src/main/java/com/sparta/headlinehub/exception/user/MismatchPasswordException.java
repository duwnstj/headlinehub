package com.sparta.headlinehub.exception.user;

/* 비밀번호 불 일치 */
public class MismatchPasswordException extends RuntimeException{
    public MismatchPasswordException(String msg) {
        super(msg);
    }
}
