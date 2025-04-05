package com.bzetab.ogge.auth_gestion_users.service;

import com.bzetab.ogge.auth_gestion_users.model.dto.UserDTO;
import com.bzetab.ogge.auth_gestion_users.model.entities.User;
import com.bzetab.ogge.auth_gestion_users.model.enums.Role;
import com.bzetab.ogge.auth_gestion_users.model.request.UserRegisterRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserRegisterRequest userRegisterRequest);
    List<User> getUsers();
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    Role findRoleUserByEmail(String email);
    User updateUser(UserDTO userDTO, User existingUser);
}
