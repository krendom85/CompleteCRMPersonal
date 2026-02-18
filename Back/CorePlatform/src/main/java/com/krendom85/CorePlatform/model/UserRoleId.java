package com.krendom85.CorePlatform.model;

import java.io.Serializable;

public class UserRoleId implements Serializable {
    private Long user;
    private Long role;

    public UserRoleId() {}

    public UserRoleId(Long user, Long role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleId that = (UserRoleId) o;
        return java.util.Objects.equals(user, that.user) && java.util.Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(user, role);
    }
}
