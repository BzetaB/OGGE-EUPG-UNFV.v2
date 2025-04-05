package com.bzetab.ogge.auth_gestion_users.service;

import com.bzetab.ogge.auth_gestion_users.model.dto.GraduateDTO;
import com.bzetab.ogge.auth_gestion_users.model.dto.UserDTO;
import com.bzetab.ogge.auth_gestion_users.model.entities.Graduate;
import com.bzetab.ogge.auth_gestion_users.model.request.UserRegisterRequest;

import java.util.List;
import java.util.Optional;

public interface GraduateService {
    Graduate createGraduate(UserRegisterRequest userRegisterRequest);
    List<Graduate> getGraduates();
    Graduate updateGraduate(GraduateDTO graduateDTO, UserDTO userDTO);
    Optional<Graduate> findGraduateById(Long id);
    Optional<Graduate> findGraduateByDocumentNumberGraduate(String documentNumber);
    Optional<Graduate> findGraduateByEmail(String email);
}
