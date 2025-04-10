package com.bzetab.ogge.auth_gestion_users.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GraduateDTO extends UserDTO{
    private String currentDegree;
    private String aspireDegree;
}
