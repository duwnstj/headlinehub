package com.sparta.headlinehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.headlinehub.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Optional<Comment> findByBoardIdAndId(Long boardId, Long commentId);

    List<Comment> findAllByBoardIdOrderByModifiedDateDesc(Long boardId);
}
