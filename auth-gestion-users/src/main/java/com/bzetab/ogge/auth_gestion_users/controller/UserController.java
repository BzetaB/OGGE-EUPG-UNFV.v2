package com.bzetab.ogge.auth_gestion_users.controller;

import com.bzetab.ogge.auth_gestion_users.model.dto.UserDTO;
import com.bzetab.ogge.auth_gestion_users.model.entities.User;
import com.bzetab.ogge.auth_gestion_users.model.enums.Role;
import com.bzetab.ogge.auth_gestion_users.model.request.UserRegisterRequest;
import com.bzetab.ogge.auth_gestion_users.service.imp.UserServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ogge-eupg-unfv/gestion-usuarios/usuario")
public class UserController {

    private final UserServiceImp userService;

    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @PostMapping("/registrar-usuario")
    public ResponseEntity<User> createUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok(userService.createUser(userRegisterRequest));
    }

    @GetMapping("/lista-usuarios")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/buscar-rol")
    public ResponseEntity<Role> findRoleUserByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.findRoleUserByEmail(email));
    }

    @PutMapping("/actualizar-usuario/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }


}
