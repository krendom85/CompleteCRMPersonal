package com.krendom85.CorePlatform.mapper;

import com.krendom85.CorePlatform.dto.RoleDTO;
import com.krendom85.CorePlatform.model.Role;
import com.krendom85.CorePlatform.model.Company;

public class RoleMapper {
    public static Role toEntity(RoleDTO dto, Company company) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setCompany(company);
        return role;
    }

    public static RoleDTO toDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setCompanyId(role.getCompany() != null ? role.getCompany().getId() : null);
        return dto;
    }
}
