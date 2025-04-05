package com.bzetab.ogge.auth_gestion_users.repository;

import com.bzetab.ogge.auth_gestion_users.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailUser(String email);
    Optional<User> findUserByGraduate_IdGraduate(Long graduateIdGraduate);
}
