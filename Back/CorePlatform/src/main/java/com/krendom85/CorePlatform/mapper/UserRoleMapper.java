package com.krendom85.CorePlatform.mapper;

import com.krendom85.CorePlatform.dto.UserRoleDTO;
import com.krendom85.CorePlatform.model.UserRole;
import com.krendom85.CorePlatform.model.User;
import com.krendom85.CorePlatform.model.Role;

public class UserRoleMapper {
    public static UserRole toEntity(UserRoleDTO dto, User user, Role role) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        return userRole;
    }

    public static UserRoleDTO toDTO(UserRole userRole) {
        UserRoleDTO dto = new UserRoleDTO();
        dto.setUserId(userRole.getUser().getId());
        dto.setRoleId(userRole.getRole().getId());
        return dto;
    }
}
