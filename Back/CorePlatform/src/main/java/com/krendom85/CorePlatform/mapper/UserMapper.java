package com.krendom85.CorePlatform.mapper;

import com.krendom85.CorePlatform.dto.UserDTO;
import com.krendom85.CorePlatform.model.User;
import com.krendom85.CorePlatform.model.Company;

public class UserMapper {
    public static User toEntity(UserDTO dto, Company company) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setCompany(company);
        user.setAvatarUrl(dto.getAvatarUrl());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setIsSuperUser(dto.getIsSuperUser());
        user.setStatus(dto.getStatus());
        user.setLocale(dto.getLocale());
        user.setTimezone(dto.getTimezone());
        return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCompanyId(user.getCompany() != null ? user.getCompany().getId() : null);
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setIsSuperUser(user.getIsSuperUser());
        dto.setStatus(user.getStatus());
        dto.setLocale(user.getLocale());
        dto.setTimezone(user.getTimezone());
        return dto;
    }
}
