package com.bzetab.ogge.auth_gestion_users.service;

import com.bzetab.ogge.auth_gestion_users.model.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getEmployees();
    Optional<Employee> findEmployeeById(Long id);
    Optional<Employee> findEmployeeByEmail(String email);
    Optional<Employee> findEmployeeByDocumentNumber(String documentNumber);
}
