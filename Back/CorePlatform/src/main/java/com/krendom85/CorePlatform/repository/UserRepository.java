package com.krendom85.CorePlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krendom85.CorePlatform.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}
