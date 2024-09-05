package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.annotation.Auth;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.comment.request.PostSaveCommentRequestDto;
import com.sparta.headlinehub.dto.comment.response.PostSaveCommentResponseDto;
import com.sparta.headlinehub.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardId}/comment")
    public PostSaveCommentResponseDto saveComment(
            @PathVariable Long boardId,
            @RequestBody PostSaveCommentRequestDto requestDto,
            @Auth AuthUser authUser) {
        return commentService.saveComment(boardId ,authUser, requestDto);

    }
//
//    @GetMapping("/board/{boardId}")
//    public List<GetCommentListResponseDto> getComment(@PathVariable Long boardId){
//
//        return commentService.getComment(boardId);
//    }

}
