package com.sparta.headlinehub.exception.profile;

public class SamePasswordException extends RuntimeException {
    public SamePasswordException(String s) {
        super(s);
    }
}
