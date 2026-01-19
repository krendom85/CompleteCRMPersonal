package com.krendom85.CorePlatform.repository.Permission;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krendom85.CorePlatform.domain.Permission.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    
}
