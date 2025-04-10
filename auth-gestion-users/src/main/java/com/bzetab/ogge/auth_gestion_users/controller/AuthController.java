package com.bzetab.ogge.auth_gestion_users.controller;

import com.bzetab.ogge.auth_gestion_users.config.JwtTokenProvider;
import com.bzetab.ogge.auth_gestion_users.model.entities.Employee;
import com.bzetab.ogge.auth_gestion_users.model.entities.Graduate;
import com.bzetab.ogge.auth_gestion_users.model.request.EmployeeRegisterRequesst;
import com.bzetab.ogge.auth_gestion_users.model.request.GraduateRegisterRequest;
import com.bzetab.ogge.auth_gestion_users.model.request.LoginRequest;
import com.bzetab.ogge.auth_gestion_users.model.response.JwtAuthenticationResponse;
import com.bzetab.ogge.auth_gestion_users.service.EmployeeService;
import com.bzetab.ogge.auth_gestion_users.service.GraduateService;
import com.bzetab.ogge.auth_gestion_users.utils.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ogge-eupg-unfv/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final GraduateService graduateService;
    private final EmployeeService employeeService;
    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            GraduateService graduateService,
            EmployeeService employeeService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.graduateService = graduateService;
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return ResponseEntity.ok(JwtAuthenticationResponse.builder()
                .accessToken(jwt)
                .tokenType(new JwtAuthenticationResponse().getTokenType())
                .roles(roles)
                .build());
    }

    @PostMapping("/register-egresado")
    public ResponseEntity<GeneralResponse> registerGraduate(@RequestBody GraduateRegisterRequest registerRequest){
        Graduate graduate = graduateService.createEgresado(registerRequest);
        return ResponseEntity.ok(graduateService.generateGraduateDTO(graduate));
    }

    @PostMapping("/register-empleado")
    public ResponseEntity<GeneralResponse> registerGraduate(@RequestBody EmployeeRegisterRequesst registerRequest){
        Employee employee = employeeService.createEmployee(registerRequest);
        return ResponseEntity.ok(employeeService.generatedEmployeeDTO(employee));
    }

}
