package com.sparta.headlinehub.dto.profile.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetProfileSimpleResponseDto {
    private final String title;
    private final String content;
    private final LocalDateTime creationDate;
    private final LocalDateTime modifiedDate;

    public GetProfileSimpleResponseDto(String title, String content, LocalDateTime creationDate, LocalDateTime modifiedDate) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.modifiedDate = modifiedDate;
    }
}
