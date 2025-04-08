package com.bzetab.ogge.auth_gestion_users.repository;

import com.bzetab.ogge.auth_gestion_users.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByNameRolIn(List<String> nameRols);
}
