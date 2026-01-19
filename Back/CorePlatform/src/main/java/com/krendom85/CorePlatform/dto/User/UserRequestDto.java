package com.krendom85.CorePlatform.dto.User;



import java.util.List;

import com.krendom85.CorePlatform.domain.enums.StatesEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String email;
    private String username;
    private String password;
    private StatesEnum status; // O StatesEnum si lo prefieres
    private Long companyId;
    private List<Long> roleIds; // Opcional, si quieres asignar roles al crear el usuario

}
