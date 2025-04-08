package com.bzetab.ogge.auth_gestion_users.service;

import com.bzetab.ogge.auth_gestion_users.model.entities.Users;

import java.util.List;

public interface UserService {
    Users createUser(String email, String password, List<String> roles);
    List<Users> getUsers();
}
