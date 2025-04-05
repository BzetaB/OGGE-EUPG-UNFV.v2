package com.bzetab.ogge.auth_gestion_users.model.dto;

import com.bzetab.ogge.auth_gestion_users.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String emailUser;
    private String passwordUser;
    private Boolean statusUser;
    private Role role;
}
