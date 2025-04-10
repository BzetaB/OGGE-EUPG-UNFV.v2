package com.bzetab.ogge.auth_gestion_users.model.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRegisterRequesst extends RegisterRequest{
    private LocalDateTime dateAdmissionEmployee;
}
