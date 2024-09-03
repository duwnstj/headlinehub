package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
