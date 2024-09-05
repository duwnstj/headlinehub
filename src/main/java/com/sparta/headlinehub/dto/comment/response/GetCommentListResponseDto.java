package com.sparta.headlinehub.dto.comment.response;

import com.sparta.headlinehub.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentListResponseDto {
    private String userName;
    private String comment;
    private LocalDateTime creation_Date;
    private LocalDateTime modified_Date;

    public GetCommentListResponseDto(Comment comment) {
        this.userName = comment.getUserName();
        this.comment = comment.getComment();
        this.creation_Date = comment.getCreationDate();
        this.modified_Date = comment.getModifiedDate();
    }
}
