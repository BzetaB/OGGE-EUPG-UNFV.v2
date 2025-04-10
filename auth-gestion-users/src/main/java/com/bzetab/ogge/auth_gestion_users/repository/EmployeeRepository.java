package com.bzetab.ogge.auth_gestion_users.repository;

import com.bzetab.ogge.auth_gestion_users.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeByDocumentNumberEmployee(String documentNumberEmployee);
    Optional<Employee> findEmployeeByUser_EmailUser(String userEmailUser);
    Optional<Employee> findEmployeeByCellphoneEmployee(String cellphoneEmployee);
}
