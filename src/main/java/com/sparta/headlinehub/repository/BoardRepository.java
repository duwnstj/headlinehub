package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long> {


    Page<Board> findByUserIdOrderByCreationDateDesc(Long userId, Pageable pageable);


}
