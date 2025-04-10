package com.bzetab.ogge.auth_gestion_users.service.imp;

import com.bzetab.ogge.auth_gestion_users.model.dto.EmployeeDTO;
import com.bzetab.ogge.auth_gestion_users.model.entities.Employee;
import com.bzetab.ogge.auth_gestion_users.model.entities.Users;
import com.bzetab.ogge.auth_gestion_users.model.enums.DocumentType;
import com.bzetab.ogge.auth_gestion_users.model.request.EmployeeRegisterRequesst;
import com.bzetab.ogge.auth_gestion_users.repository.EmployeeRepository;
import com.bzetab.ogge.auth_gestion_users.service.EmployeeService;
import com.bzetab.ogge.auth_gestion_users.service.UserService;
import com.bzetab.ogge.auth_gestion_users.utils.GeneralResponse;
import org.springframework.stereotype.Service;

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


    @Override
    public Employee createEmployee(EmployeeRegisterRequesst request) {
        if(request.getDocumentNumber().isEmpty()){
            throw new RuntimeException("El documento no puede estar vacio");
        }
        if(employeeRepository.findEmployeeByDocumentNumberEmployee(request.getDocumentNumber()).isPresent()){
            throw new RuntimeException("El documento ya existe");
        }
        if(request.getCellphone().isEmpty()){
            throw new RuntimeException("El cellphone no puede estar vacio");
        }
        if(employeeRepository.findEmployeeByCellphoneEmployee(request.getCellphone()).isPresent()){
            throw new RuntimeException("El cellphone ya existe");
        }
        List<String> rol = request.getRoles();
        Users users = userService.createUser(request.getEmail(), request.getPassword(), rol);

        Employee employee = Employee.builder()
                .nameEmployee(request.getName())
                .lastNameEmployee(request.getLastName())
                .documentType(DocumentType.valueOf(request.getDocumentType()))
                .documentNumberEmployee(request.getDocumentNumber())
                .cellphoneEmployee(request.getCellphone())
                .activeEmployee(true)
                .dateAdmissionEmployee(request.getDateAdmissionEmployee())
                .user(users)
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public GeneralResponse generatedEmployeeDTO(Employee employee) {

        List<String> roles = employee.getUser().getRole().stream()
                .map(rol -> rol.getRole().getNameRol())
                .toList();

        return GeneralResponse.builder()
                .message("Empleado registrado correctamente")
                .data(EmployeeDTO.builder()
                        .name(employee.getNameEmployee())
                        .lastName(employee.getLastNameEmployee())
                        .documentType(employee.getDocumentType().toString())
                        .documentNumber(employee.getDocumentNumberEmployee())
                        .cellphone(employee.getCellphoneEmployee())
                        .email(employee.getUser().getEmailUser())
                        .roles(roles)
                        .active(employee.getActiveEmployee())
                        .dateAdmission(employee.getDateAdmissionEmployee())
                        .dateDeparture(employee.getDepartureEmployee())
                        .dateCreated(employee.getCreatedAt())
                        .build())
                .build();
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
