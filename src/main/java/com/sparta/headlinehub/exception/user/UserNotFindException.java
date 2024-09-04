package com.sparta.headlinehub.exception.user;

/* 유저를 찾지 못했을 때 */
public class UserNotFindException extends RuntimeException{
    public UserNotFindException(String msg) {
        super(msg);
    }
}
