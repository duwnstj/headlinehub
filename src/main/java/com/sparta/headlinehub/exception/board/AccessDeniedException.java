package com.sparta.headlinehub.exception.board;


public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String messeage){
        super(messeage);
    }
}
