package com.bzetab.ogge.auth_gestion_users.repository;

import com.bzetab.ogge.auth_gestion_users.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByDocumentNumberEmployee(String documentNumberEmployee);
    Optional<Employee> findEmployeeBycellphoneEmployee(String cellphoneEmployee);
    Optional<Employee> findEmployeeByUser_EmailUser(String userEmailUser);
}
