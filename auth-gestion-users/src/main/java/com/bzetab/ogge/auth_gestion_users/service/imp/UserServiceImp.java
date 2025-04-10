package com.bzetab.ogge.auth_gestion_users.service.imp;

import com.bzetab.ogge.auth_gestion_users.model.entities.Role;
import com.bzetab.ogge.auth_gestion_users.model.entities.UserRole;
import com.bzetab.ogge.auth_gestion_users.model.entities.Users;
import com.bzetab.ogge.auth_gestion_users.repository.RoleRepository;
import com.bzetab.ogge.auth_gestion_users.repository.UserRepository;
import com.bzetab.ogge.auth_gestion_users.service.UserService;
import com.bzetab.ogge.auth_gestion_users.utils.exception.custom.AlreadyExist;
import com.bzetab.ogge.auth_gestion_users.utils.exception.custom.NotEmpty;
import com.bzetab.ogge.auth_gestion_users.utils.exception.custom.NotFound;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public Users createUser(String email, String password, List<String> roles) {
        if (userRepository.findUserByEmailUser(email).isPresent()) {
            throw new AlreadyExist("El correo ya se encuentra registrado");
        }
        if(password.isEmpty()){
            throw new NotEmpty("La contraseña no puede estar vacia");
        }
        if(roles.isEmpty()){
            throw new NotEmpty("Se necesita asignarle un rol");
        }
        List<Role> rolesFound = roleRepository.findByNameRolIn(roles);
        if(rolesFound.isEmpty()){
            throw new NotFound("No se encontraron los roles");
        }

        String encondedPassword = passwordEncoder.encode(password);

        Users newUser = Users.builder()
                .emailUser(email)
                .passwordUser(encondedPassword)
                .statusUser(true)
                .createdAt(LocalDateTime.now())
                .build();

        Set<UserRole> rolesUser = new HashSet<>();
        for(Role role : rolesFound){
            UserRole userRole = UserRole.builder()
                    .role(role)
                    .users(newUser)
                    .active(true)
                    .build();
            rolesUser.add(userRole);
        }
        newUser.setRole(rolesUser);
        return userRepository.save(newUser);
    }

    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }
}
