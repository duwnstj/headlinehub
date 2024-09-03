package com.sparta.headlinehub.dto.board.response;

import com.sparta.headlinehub.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetDetailResponseDto {

    private Long id;
    private String title;
    private String content;

    public GetDetailResponseDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }

}
