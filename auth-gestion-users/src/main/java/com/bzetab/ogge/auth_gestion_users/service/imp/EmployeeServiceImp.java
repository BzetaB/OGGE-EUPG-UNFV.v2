package com.bzetab.ogge.auth_gestion_users.service.imp;

import com.bzetab.ogge.auth_gestion_users.model.entities.Employee;
import com.bzetab.ogge.auth_gestion_users.repository.EmployeeRepository;
import com.bzetab.ogge.auth_gestion_users.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }


    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        return employeeRepository.findEmployeeByUser_EmailUser(email);
    }

    @Override
    public Optional<Employee> findEmployeeByDocumentNumber(String documentNumber) {
        return employeeRepository.findEmployeeByDocumentNumberEmployee(documentNumber);
    }
}
