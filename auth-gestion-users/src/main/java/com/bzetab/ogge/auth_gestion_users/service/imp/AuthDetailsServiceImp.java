package com.bzetab.ogge.auth_gestion_users.service.imp;

import com.bzetab.ogge.auth_gestion_users.model.entities.UserRole;
import com.bzetab.ogge.auth_gestion_users.model.entities.Users;
import com.bzetab.ogge.auth_gestion_users.repository.UserRepository;
import com.bzetab.ogge.auth_gestion_users.utils.exception.custom.NotFound;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthDetailsServiceImp implements UserDetailsService {
    private final UserRepository userRepository;

    public AuthDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findUserByEmailUser(email)
                .orElseThrow(() -> new NotFound("Usuario no encontrado con el email: " + email));

        List<GrantedAuthority> authorities = user.getRole()
                .stream()
                .filter(UserRole::getActive)
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getNameRol()))
                .collect(Collectors.toList());
        return new User(user.getEmailUser(), user.getPasswordUser(), authorities);
    }
}
