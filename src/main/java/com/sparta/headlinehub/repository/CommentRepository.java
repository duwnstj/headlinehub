package com.sparta.headlinehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.headlinehub.entity.Comment;
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
