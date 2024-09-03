package com.sparta.headlinehub.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }
}
