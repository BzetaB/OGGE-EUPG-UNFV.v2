package com.bzetab.ogge.auth_gestion_users.model.request;

import com.bzetab.ogge.auth_gestion_users.model.dto.EmployeeDTO;
import com.bzetab.ogge.auth_gestion_users.model.dto.UserDTO;
import lombok.Data;

@Data
public class UpdateEmployeeRequest {
    private EmployeeDTO employeeDTO;
    private UserDTO userDTO;
}
