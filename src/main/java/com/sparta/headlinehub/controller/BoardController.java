package com.sparta.headlinehub.controller;

import com.sparta.headlinehub.annotation.Auth;
import com.sparta.headlinehub.dto.AuthUser;
import com.sparta.headlinehub.dto.board.request.PostSaveRequestDto;
import com.sparta.headlinehub.dto.board.request.PostUpdateRequestDto;
import com.sparta.headlinehub.dto.board.response.GetDetailResponseDto;
import com.sparta.headlinehub.dto.board.response.PostSaveResponseDto;
import com.sparta.headlinehub.dto.board.response.PostUpdateResponseDto;
import com.sparta.headlinehub.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * 게시물 저장
     *
     * @param requestDto 유저 고유 아이디, 제목 , 내용
     * @return 게시물 제목
     */
    @PostMapping
    public ResponseEntity<PostSaveResponseDto> saveBoard(
            @RequestBody PostSaveRequestDto requestDto,
            @Auth AuthUser user) {
        log.info("user.getId : " + user.getId());
        return ResponseEntity.ok(boardService.saveBoard(user ,requestDto));

    }

    /**
     * 유저 뉴스피드 전체 조회
     * 조건 : 내가쓴 뉴스피드만 볼 수 있다.
     *
     * @return userDto.userName(이름) ,title(제목),content(내용),creationdate,modifieddate
     * @Param page, size(페이지), userId(유저 고유 아이디)
     */
    @GetMapping("/param")
    public ResponseEntity<Page<GetDetailResponseDto>> getUsersBoards(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @Auth AuthUser authUser) {
        return ResponseEntity.ok(boardService.getUsersBoards(page, size, authUser));
    }

    /**
     * 게시물 수정
     * 조건 : 게시물 작성한 사람만 수정 가능함
     *
     * @return title, content,userName
     * @Param boardId(게시물 고유번호), requestDto(title, content)
     */
    @PutMapping("/{boardId}")
    public ResponseEntity<PostUpdateResponseDto> updateBoard(
            @PathVariable Long boardId,
            @Auth AuthUser authUser,
            @RequestBody PostUpdateRequestDto requestDto
    ) {

        return ResponseEntity.ok(boardService.updateboard(boardId, authUser, requestDto));
    }

    /**
     * 게시물 삭제
     * 조건 : 게시물 작성한 사람만 삭제 가능함
     * <p>
     * //
     *
     * @Param userId
     */

    @DeleteMapping("/{boardId}")
    public void deleteBoard(
            @PathVariable Long boardId,
            @Auth AuthUser authUser) {
        boardService.deleteBoard(boardId, authUser);
    }

}
