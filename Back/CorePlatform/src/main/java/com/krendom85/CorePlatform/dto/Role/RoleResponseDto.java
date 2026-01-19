package com.krendom85.CorePlatform.dto.Role;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RoleResponseDto {
    private Long id;
    private String name;
    private String description;
    private List<String> permissions; // Nombres de los permisos asignados
}
