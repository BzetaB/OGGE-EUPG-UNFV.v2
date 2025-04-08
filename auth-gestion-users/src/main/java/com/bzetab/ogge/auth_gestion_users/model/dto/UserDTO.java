package com.bzetab.ogge.auth_gestion_users.model.dto;

import com.bzetab.ogge.auth_gestion_users.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String emailUser;
    private String passwordUser;
    private Boolean statusUser;
    private List<Role> roles;
}
