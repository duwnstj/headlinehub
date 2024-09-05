package com.sparta.headlinehub.repository;

import com.sparta.headlinehub.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {

}
