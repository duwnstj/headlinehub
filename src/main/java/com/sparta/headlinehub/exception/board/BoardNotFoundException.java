package com.sparta.headlinehub.exception.board;

public class BoardNotFoundException extends RuntimeException {

    // 리소스(자원)을 찾을 수 없을 때
    public BoardNotFoundException(String message){
        super(message);
    }
}
