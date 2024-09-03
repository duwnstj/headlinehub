package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.User;
import com.sparta.headlinehub.exception.user.UserNotFindException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new UserNotFindException("유저를 찾을 수 없습니다."));
    }
}
