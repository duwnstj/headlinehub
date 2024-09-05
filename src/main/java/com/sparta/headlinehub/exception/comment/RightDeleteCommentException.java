package com.sparta.headlinehub.exception.comment;

/* 댓글 삭제 권한이 없음 */
public class RightDeleteCommentException extends RuntimeException{
    public RightDeleteCommentException(String msg) {
        super(msg);
    }
}
