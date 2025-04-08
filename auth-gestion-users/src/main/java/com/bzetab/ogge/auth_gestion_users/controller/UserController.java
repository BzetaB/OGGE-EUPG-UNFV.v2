package com.bzetab.ogge.auth_gestion_users.controller;

import com.bzetab.ogge.auth_gestion_users.model.entities.Users;
import com.bzetab.ogge.auth_gestion_users.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ogge-eupg-unfv/gestion-usuarios/usuario")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/lista-usuarios")
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

}
