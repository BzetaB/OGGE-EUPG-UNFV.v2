package com.bzetab.ogge.auth_gestion_users.service.imp;

import com.bzetab.ogge.auth_gestion_users.model.dto.EmployeeDTO;
import com.bzetab.ogge.auth_gestion_users.model.dto.UserDTO;
import com.bzetab.ogge.auth_gestion_users.model.entities.Employee;
import com.bzetab.ogge.auth_gestion_users.model.entities.User;
import com.bzetab.ogge.auth_gestion_users.model.enums.Role;
import com.bzetab.ogge.auth_gestion_users.model.request.UserRegisterRequest;
import com.bzetab.ogge.auth_gestion_users.repository.EmployeeRepository;
import com.bzetab.ogge.auth_gestion_users.service.EmployeeService;
import com.bzetab.ogge.auth_gestion_users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    
    public EmployeeServiceImp(EmployeeRepository employeeRepository, UserService userService) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
    }
    
    @Transactional
    @Override
    public Employee createEmployee(UserRegisterRequest userRegisterRequest) {
        if (employeeRepository.findEmployeeBycellphoneEmployee(userRegisterRequest.getCellphone()).isPresent()) {
            throw new RuntimeException("Employee Cellphone already exists");
        }

        if(employeeRepository.findEmployeeByDocumentNumberEmployee(userRegisterRequest.getDocumentNumber()).isPresent()) {
            throw new RuntimeException("Graduate Document number already exists");
        }

        User userGraduate = userService.createUser(userRegisterRequest);
        userGraduate.setRole(Role.TRABAJADOR);

        Employee employee = Employee.builder()
                .nameEmployee(userRegisterRequest.getName())
                .lastNameEmployee(userRegisterRequest.getLastName())
                .documentType(userRegisterRequest.getDocumentType())
                .documentNumberEmployee(userRegisterRequest.getDocumentNumber())
                .cellphoneEmployee(userRegisterRequest.getCellphone())
                .activeEmployee(true)
                .createdAt(LocalDateTime.now())
                .dateAdmissionEmployee(userRegisterRequest.getDateAdmission())
                .createdAt(LocalDateTime.now())
                .user(userGraduate)
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    
    @Transactional
    @Override
    public Employee updateEmployee(EmployeeDTO employeeDTO, UserDTO userDTO) {
        Employee updateEmployee = this.findEmployeeById(employeeDTO.getIdEmployee())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        User user = updateEmployee.getUser();
        if (userDTO != null) {
            if (!updateEmployee.getUser().getIdUser().equals(userDTO.getId())) {
                throw new RuntimeException("Employee user id does not match");
            }
            userService.updateUser(userDTO, user);
        }
        if(employeeDTO.getName() != null) {
            updateEmployee.setNameEmployee(employeeDTO.getName());
        }
        if(employeeDTO.getLastName() != null) {
            updateEmployee.setLastNameEmployee(employeeDTO.getLastName());
        }
        if(employeeDTO.getDocumentType() != null) {
            updateEmployee.setDocumentType(employeeDTO.getDocumentType());
        }
        if(employeeDTO.getDocumentNumber() != null && !employeeDTO.getDocumentNumber().equals(updateEmployee.getDocumentNumberEmployee())) {
            if(this.findEmployeeByDocumentNumber(employeeDTO.getDocumentNumber()).isPresent()) {
                throw new RuntimeException("Graduate Document number already exists");
            }
            updateEmployee.setDocumentNumberEmployee(employeeDTO.getDocumentNumber());

        }
        if(employeeDTO.getCellphone() != null && !employeeDTO.getCellphone().equals(updateEmployee.getCellphoneEmployee())) {
            if(employeeRepository.findEmployeeBycellphoneEmployee(employeeDTO.getCellphone()).isPresent()) {
                throw new RuntimeException("Graduate Cellphone already exists");
            }
            updateEmployee.setCellphoneEmployee(employeeDTO.getCellphone());
        }
        if (employeeDTO.getActive() != null) {
            updateEmployee.setActiveEmployee(employeeDTO.getActive());
        }
        if (employeeDTO.getDateAdmission() != null) {
            updateEmployee.setDateAdmissionEmployee(employeeDTO.getDateAdmission());
        }
        if(employeeDTO.getDateDeparture() != null) {
            updateEmployee.setDepartureEmployee(employeeDTO.getDateDeparture());
        }
        
        return employeeRepository.save(updateEmployee);
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
