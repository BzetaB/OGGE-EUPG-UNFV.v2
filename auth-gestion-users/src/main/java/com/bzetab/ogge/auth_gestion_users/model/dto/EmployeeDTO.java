package com.bzetab.ogge.auth_gestion_users.model.dto;

import com.bzetab.ogge.auth_gestion_users.model.enums.DocumentType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EmployeeDTO {
    private Long idEmployee;
    private String name;
    private String lastName;
    private DocumentType documentType;
    private String documentNumber;
    private String cellphone;
    private Boolean active;
    private LocalDateTime dateAdmission;
    private LocalDateTime dateDeparture;
}
