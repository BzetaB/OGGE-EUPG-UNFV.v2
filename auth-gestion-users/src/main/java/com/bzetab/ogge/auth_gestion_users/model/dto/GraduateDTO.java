package com.bzetab.ogge.auth_gestion_users.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GraduateDTO {
    private String nameGraduate;
    private String lastNameGraduate;
    private String emailGraduate;
    private String typeDocument;
    private String documentNumber;
    private String cellphone;
    private String currentDegree;
    private String aspireDegree;
}
