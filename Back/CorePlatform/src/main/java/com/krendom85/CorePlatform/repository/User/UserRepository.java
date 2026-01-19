package com.krendom85.CorePlatform.repository.User;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krendom85.CorePlatform.domain.User.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    
}
