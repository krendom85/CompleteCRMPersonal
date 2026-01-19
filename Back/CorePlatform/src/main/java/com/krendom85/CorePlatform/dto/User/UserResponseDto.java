package com.krendom85.CorePlatform.dto.User;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String email;
    private String username;
    private String status; // O StatesEnum si lo prefieres
    private Instant createdAt;
    private Instant updatedAt;
    private Long companyId;
    private String companyName; // Opcional, para mostrar el nombre de la empresa
    private List<String> roles; // Lista de nombres de roles asignados
}
