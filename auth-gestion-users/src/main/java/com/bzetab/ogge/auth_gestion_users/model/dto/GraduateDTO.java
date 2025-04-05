package com.bzetab.ogge.auth_gestion_users.model.dto;

import com.bzetab.ogge.auth_gestion_users.model.enums.Degree;
import com.bzetab.ogge.auth_gestion_users.model.enums.DocumentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GraduateDTO {
    private Long idGraduate;
    private String name;
    private String lastName;
    private DocumentType documentType;
    private String documentNumber;
    private String cellphone;
    private Boolean active;
    private Degree currentDegree;
    private Degree aspiredDegree;
}
