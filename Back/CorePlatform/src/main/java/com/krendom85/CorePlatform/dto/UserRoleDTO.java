package com.krendom85.CorePlatform.dto;

import jakarta.validation.constraints.NotNull;

public class UserRoleDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Long roleId;

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
