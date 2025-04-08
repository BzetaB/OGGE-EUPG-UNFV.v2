package com.bzetab.ogge.auth_gestion_users.model.request;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GraduateRegisterRequest extends  RegisterRequest{
    private String nameGraduate;
    private String lastNameGraduate;
    private String documentType;
    private String documentNumber;
    private String cellphone;
    private String currentDegree;
    private String aspireDegree;
}
