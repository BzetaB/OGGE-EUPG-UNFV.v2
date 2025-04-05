package com.bzetab.ogge.auth_gestion_users.repository;

import com.bzetab.ogge.auth_gestion_users.model.entities.Graduate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GraduateRepository extends JpaRepository<Graduate, Long> {
    Optional<Graduate> findGraduateByDocumentNumberGraduate(String documentNumber);
    Optional<Graduate> findGraduateByCellphoneGraduate(String email);
    Optional<Graduate> findGraduateByUser_EmailUser(String userEmailUser);
}
