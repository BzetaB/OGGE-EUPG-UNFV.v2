package com.bzetab.ogge.auth_gestion_users.service.imp;

import com.bzetab.ogge.auth_gestion_users.model.dto.UserDTO;
import com.bzetab.ogge.auth_gestion_users.model.entities.User;
import com.bzetab.ogge.auth_gestion_users.model.enums.Role;
import com.bzetab.ogge.auth_gestion_users.model.request.UserRegisterRequest;
import com.bzetab.ogge.auth_gestion_users.repository.UserRepository;
import com.bzetab.ogge.auth_gestion_users.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserRegisterRequest userRegisterRequest) {

        if (this.findUserByEmail(userRegisterRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User registerUser = User.builder()
                .emailUser(userRegisterRequest.getEmail())
                .passwordUser(userRegisterRequest.getPassword())
                .statusUser(true)
                .role(Role.ADMIN)
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(registerUser);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmailUser(email);
    }

    @Override
    public Role findRoleUserByEmail(String email) {

        User userFound = this.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userFound.getRole();
    }

    @Override
    public User updateUser(UserDTO userDTO, User existingUser) {
        User updateUser = existingUser != null ? existingUser :
                this.findUserById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userDTO.getPasswordUser() != null) {
            updateUser.setPasswordUser(userDTO.getPasswordUser());
        }
        if (userDTO.getEmailUser() != null && userDTO.getEmailUser().equals(updateUser.getEmailUser())) {
            if (userRepository.findUserByEmailUser(updateUser.getEmailUser()).isPresent()) {
                throw new RuntimeException("Email already exists");
            }
            updateUser.setEmailUser(userDTO.getEmailUser());
        }

        if (userDTO.getStatusUser() != null) {
            updateUser.setStatusUser(userDTO.getStatusUser());
        }

        return userRepository.save(updateUser);
    }
}
