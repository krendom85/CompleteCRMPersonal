package com.krendom85.CorePlatform.model;

import java.io.Serializable;

public class RolePermissionId implements Serializable {
    private Long role;
    private Long permission;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RolePermissionId that = (RolePermissionId) obj;
        return role.equals(that.role) && permission.equals(that.permission);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
