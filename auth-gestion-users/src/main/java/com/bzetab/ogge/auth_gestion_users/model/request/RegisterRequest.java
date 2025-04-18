package com.bzetab.ogge.auth_gestion_users.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String cellphone;
    private String email;
    private String password;
    private List<String> roles;
}
