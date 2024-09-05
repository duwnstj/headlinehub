package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowingIdAndFollowerId(Long followingId, Long FollowerId);

    List<Follow> findByFollowingId(Long userId);

    List<Follow> findAllByFollowingId(Long followingId);

    List<Follow> findAllByFollowerId(Long followerId);

    Follow findByFollowingIdAndFollowerId(Long followingId, Long FollowerId);
}
