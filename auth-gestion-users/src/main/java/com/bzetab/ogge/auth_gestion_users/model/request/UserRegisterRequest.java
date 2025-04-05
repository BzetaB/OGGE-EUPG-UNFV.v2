package com.bzetab.ogge.auth_gestion_users.model.request;

import com.bzetab.ogge.auth_gestion_users.model.enums.DocumentType;
import com.bzetab.ogge.auth_gestion_users.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterRequest {
    private String name;
    private String lastName;
    private DocumentType documentType;
    private String documentNumber;
    private String cellphone;
    private String email;
    private String password;
    private Role role;
}
