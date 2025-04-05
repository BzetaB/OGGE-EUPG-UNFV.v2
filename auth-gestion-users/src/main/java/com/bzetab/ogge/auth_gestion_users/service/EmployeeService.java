package com.bzetab.ogge.auth_gestion_users.service;

import com.bzetab.ogge.auth_gestion_users.model.dto.EmployeeDTO;
import com.bzetab.ogge.auth_gestion_users.model.dto.UserDTO;
import com.bzetab.ogge.auth_gestion_users.model.entities.Employee;
import com.bzetab.ogge.auth_gestion_users.model.request.UserRegisterRequest;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(UserRegisterRequest userRegisterRequest);
    List<Employee> getEmployees();
    Employee updateEmployee(EmployeeDTO employeeDTO, UserDTO userDTO);
    Optional<Employee> findEmployeeById(Long id);
    Optional<Employee> findEmployeeByEmail(String email);
    Optional<Employee> findEmployeeByDocumentNumber(String documentNumber);
}
