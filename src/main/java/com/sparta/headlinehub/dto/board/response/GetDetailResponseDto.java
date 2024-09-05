package com.sparta.headlinehub.dto.board.response;

import com.sparta.headlinehub.dto.comment.response.GetCommentListResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class GetDetailResponseDto {
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime creationDate;
    private final LocalDateTime modifiedDate;
    private final List<GetCommentListResponseDto> comments;
}
