package com.krendom85.CorePlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krendom85.CorePlatform.model.UserRole;
import com.krendom85.CorePlatform.model.UserRoleId;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId>{
    
}
