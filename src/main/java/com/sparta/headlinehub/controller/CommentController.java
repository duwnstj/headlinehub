package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.annotation.Auth;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.comment.request.PostSaveCommentRequestDto;
import com.sparta.headlinehub.dto.comment.response.GetCommentListResponseDto;
import com.sparta.headlinehub.dto.comment.request.PutUpdateCommentRequestDto;
import com.sparta.headlinehub.dto.comment.response.PostSaveCommentResponseDto;
import com.sparta.headlinehub.dto.comment.response.PutUpdateCommentResponseDto;
import com.sparta.headlinehub.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성
     * @param boardId 게시판 ID
     * @param requestDto 댓글 내용
     * @param authUser 유저 ID, 유저 이메일
     * @return 댓글 내용
     */
    @PostMapping("/{boardId}/comments")
    public ResponseEntity<PostSaveCommentResponseDto> saveComment(
            @PathVariable Long boardId,
            @RequestBody PostSaveCommentRequestDto requestDto,
            @Auth AuthUser authUser) {
        return ResponseEntity.ok(commentService.saveComment(boardId, authUser, requestDto));

    }

    /**
     * 댓글 조회
     * @param boardId 게시판 ID
     * @return 댓글 작성 유저 이름, 내용, 작성일, 수정일
     */
    @GetMapping("/{boardId}/comments")
    public ResponseEntity<List<GetCommentListResponseDto>> getComment(@PathVariable Long boardId){
        return ResponseEntity.ok(commentService.getComment(boardId));
    }

    /**
     * 댓글 수정
     * @param requestDto 수정할 댓글 내용
     * @param boardId 게시판 ID
     * @param commentId 댓글 ID
     * @param authUser 유저 ID, 유저 이메일
     * @return 수정된 댓글 내용
     */
    @PutMapping("/{boardId}/comments/{commentId}/updates")
    public ResponseEntity<PutUpdateCommentResponseDto> updateComment(
            @RequestBody PutUpdateCommentRequestDto requestDto,
            @PathVariable Long boardId,
            @PathVariable Long commentId,
            @Auth AuthUser authUser
    ) {
        return ResponseEntity.ok(commentService.updateComment(requestDto,boardId,commentId,authUser));
    }

    /**
     * 댓글 삭제
     * @param authUser 유저 ID, 유저 이메일
     * @param boardId 게시판 ID
     * @param commentId 댓글 ID
     * @return 삭제한 댓글 ID
     */
    @DeleteMapping("/{boardId}/comments/{commentId}/removes")
    public ResponseEntity<Long> deleteComment(@Auth AuthUser authUser, @PathVariable Long boardId, @PathVariable Long commentId) {
        return ResponseEntity.ok(commentService.deleteComment(authUser, boardId, commentId));
    }
}
