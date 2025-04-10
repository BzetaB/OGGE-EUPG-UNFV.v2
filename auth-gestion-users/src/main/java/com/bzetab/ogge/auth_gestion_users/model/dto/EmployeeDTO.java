package com.bzetab.ogge.auth_gestion_users.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO extends UserDTO{
    private Boolean active;
    private LocalDateTime dateAdmission;
    private LocalDateTime dateDeparture;
    private LocalDateTime dateCreated;
    private List<String> roles;
}
