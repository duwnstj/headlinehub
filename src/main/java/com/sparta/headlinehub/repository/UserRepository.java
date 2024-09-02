package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
