package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.dto.board.request.PostSaveRequestDto;
import com.sparta.headlinehub.dto.board.response.PostSaveResponseDto;
import com.sparta.headlinehub.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시물 저장
    @PostMapping
    public ResponseEntity<PostSaveResponseDto> saveBoard(@RequestBody PostSaveRequestDto requestDto){

        return ResponseEntity.ok(boardService.saveBoard(requestDto));

    }
}
