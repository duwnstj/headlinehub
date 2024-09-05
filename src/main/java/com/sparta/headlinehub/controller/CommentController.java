package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.annotation.Auth;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.comment.request.PostSaveCommentRequestDto;
import com.sparta.headlinehub.dto.comment.response.PostSaveCommentResponseDto;
import com.sparta.headlinehub.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{boardId}/comment")
    public ResponseEntity<PostSaveCommentResponseDto> saveComment(
            @PathVariable Long boardId,
            @RequestBody PostSaveCommentRequestDto requestDto,
            @Auth AuthUser authUser) {
        return ResponseEntity.ok(commentService.saveComment(boardId ,authUser, requestDto));

    }
//
//    @GetMapping("/board/{boardId}")
//    public List<GetCommentListResponseDto> getComment(@PathVariable Long boardId){
//
//        return commentService.getComment(boardId);
//    }

    // 댓글 삭제
    @DeleteMapping("/{boardId}/comments/{commentId}/removes")
    public ResponseEntity<Long> deleteComment(@Auth AuthUser authUser, @PathVariable Long boardId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(authUser, boardId, commentId));
    }
}
