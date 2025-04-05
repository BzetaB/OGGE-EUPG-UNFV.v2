package com.bzetab.ogge.auth_gestion_users.model.request;

import com.bzetab.ogge.auth_gestion_users.model.enums.Degree;
import com.bzetab.ogge.auth_gestion_users.model.enums.DocumentType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

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
    private Degree currentDegree;
    private Degree aspireDegree;
    private LocalDateTime dateAdmission;
}
