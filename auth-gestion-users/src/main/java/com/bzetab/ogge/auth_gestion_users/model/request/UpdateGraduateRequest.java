package com.bzetab.ogge.auth_gestion_users.model.request;

import com.bzetab.ogge.auth_gestion_users.model.dto.GraduateDTO;
import com.bzetab.ogge.auth_gestion_users.model.dto.UserDTO;
import lombok.Data;

@Data
public class UpdateGraduateRequest {
    private GraduateDTO graduateDTO;
    private UserDTO userDTO;
}
