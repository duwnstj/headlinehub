package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NoSuchElementException("유저를 찾을 수 없습니다."));
    }
}
