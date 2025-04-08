package com.bzetab.ogge.auth_gestion_users.repository;

import com.bzetab.ogge.auth_gestion_users.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByEmailUser(String email);
}
