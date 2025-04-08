package com.bzetab.ogge.auth_gestion_users.controller;

import com.bzetab.ogge.auth_gestion_users.config.JwtTokenProvider;
import com.bzetab.ogge.auth_gestion_users.model.entities.Graduate;
import com.bzetab.ogge.auth_gestion_users.model.request.GraduateRegisterRequest;
import com.bzetab.ogge.auth_gestion_users.model.request.LoginRequest;
import com.bzetab.ogge.auth_gestion_users.model.response.JwtAuthenticationResponse;
import com.bzetab.ogge.auth_gestion_users.service.GraduateService;
import com.bzetab.ogge.auth_gestion_users.utils.GeneralResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ogge-eupg-unfv/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final GraduateService graduateService;
    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            GraduateService graduateService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.graduateService = graduateService;
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
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/register-egresado")
    public ResponseEntity<GeneralResponse> registerGraduate(@RequestBody GraduateRegisterRequest registerRequest){
        Graduate graduate = graduateService.createEgresado(registerRequest);
        return ResponseEntity.ok(graduateService.generateGraduateDTO(graduate));
    }


}
