package com.bzetab.ogge.auth_gestion_users.controller;

import com.bzetab.ogge.auth_gestion_users.model.entities.Employee;
import com.bzetab.ogge.auth_gestion_users.model.request.UpdateEmployeeRequest;
import com.bzetab.ogge.auth_gestion_users.model.request.UserRegisterRequest;
import com.bzetab.ogge.auth_gestion_users.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ogge-eupg-unfv/gestion-usuarios/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/buscar-por-id")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam("id") Long id) {
        return employeeService.findEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/buscar-por-email")
    public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam("email") String email) {
        return employeeService.findEmployeeByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/buscar-por-documento")
    public ResponseEntity<Employee> getGraduateByDocumentNumber(@RequestParam("documento") String documentNumber) {
        return employeeService.findEmployeeByDocumentNumber(documentNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping("/registrar")
    public ResponseEntity<Employee> createEmployee(@RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok(employeeService.createEmployee(userRegisterRequest));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long Id, @RequestBody UpdateEmployeeRequest updateRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(updateRequest.getEmployeeDTO(), updateRequest.getUserDTO()));
    }

}
