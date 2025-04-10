package com.bzetab.ogge.auth_gestion_users.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String cellphone;
    private String email;
}
