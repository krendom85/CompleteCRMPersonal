package com.krendom85.CorePlatform.dto.Role;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RoleRequestDto {
    private String name;
    private String description;
    private List<Long> permissionIds; // IDs de permisos a asignar
}
