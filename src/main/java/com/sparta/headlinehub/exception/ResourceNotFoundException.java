package com.sparta.headlinehub.exception;

public class ResourceNotFoundException extends RuntimeException {

    // 리소스(자원)을 찾을 수 없을 때
    public ResourceNotFoundException(String message){
        super(message);
    }
}
