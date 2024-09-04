package com.sparta.headlinehub.exception.user;

/* 이메일 중복될 때 */
public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String msg) {
        super(msg);
    }
}
