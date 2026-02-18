package com.krendom85.CorePlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krendom85.CorePlatform.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
