package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowingIdAndFollowerId(Long followingId, Long followerId);
    List<Follow> findAllByFollowingId(Long followingId);
}
