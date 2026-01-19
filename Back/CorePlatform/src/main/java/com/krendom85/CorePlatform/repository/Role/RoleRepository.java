package com.krendom85.CorePlatform.repository.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krendom85.CorePlatform.domain.Role.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    
}
