package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.Board;
import com.sparta.headlinehub.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

}
