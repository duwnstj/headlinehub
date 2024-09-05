package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowingIdAndFollowerId(Long followingId, Long FollowerId);







    List<Follow> findByFollowingId(Long userId);
}
