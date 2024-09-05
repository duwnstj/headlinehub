package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.annotation.Auth;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.board.response.PostSaveResponseDto;
import com.sparta.headlinehub.dto.comment.request.PostSaveCommentRequestDto;
import com.sparta.headlinehub.dto.comment.response.PostSaveCommentResponseDto;
import com.sparta.headlinehub.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/board/comment/{boardId}")
    public ResponseEntity<PostSaveCommentResponseDto> saveBoard(
            @PathVariable Long boardId,
            @RequestBody PostSaveCommentRequestDto requestDto,
            @Auth AuthUser authUser) {
        return ResponseEntity.ok(commentService.saveComment(boardId ,authUser, requestDto));

    }

    @GetMapping("/board/")
}
