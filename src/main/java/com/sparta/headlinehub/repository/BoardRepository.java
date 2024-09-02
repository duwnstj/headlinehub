package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> getBoardIdsByUserId(Long userId);
}
