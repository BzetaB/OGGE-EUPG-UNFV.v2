package com.bzetab.ogge.auth_gestion_users.service;

import com.bzetab.ogge.auth_gestion_users.model.entities.Graduate;
import com.bzetab.ogge.auth_gestion_users.model.request.GraduateRegisterRequest;
import com.bzetab.ogge.auth_gestion_users.utils.GeneralResponse;

import java.util.List;
import java.util.Optional;

public interface GraduateService {
    Graduate createEgresado(GraduateRegisterRequest request);
    GeneralResponse generateGraduateDTO(Graduate graduate);
    List<Graduate> getGraduates();
    Optional<Graduate> findGraduateById(Long id);
    Optional<Graduate> findGraduateByDocumentNumberGraduate(String documentNumber);
    Optional<Graduate> findGraduateByEmail(String email);
}
