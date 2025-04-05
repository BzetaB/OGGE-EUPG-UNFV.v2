package com.bzetab.ogge.auth_gestion_users.controller;

import com.bzetab.ogge.auth_gestion_users.model.entities.Graduate;
import com.bzetab.ogge.auth_gestion_users.model.request.UpdateGraduateRequest;
import com.bzetab.ogge.auth_gestion_users.model.request.UserRegisterRequest;
import com.bzetab.ogge.auth_gestion_users.service.GraduateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ogge-eupg-unfv/gestion-usuarios/egresado")
public class GraduateControlller {

    private final GraduateService graduateService;

    public GraduateControlller(GraduateService graduateService) {
        this.graduateService = graduateService;
    }

    @GetMapping("/buscar-por-email")
    public ResponseEntity<Graduate> getGraduateByUser_EmailUser(@RequestParam("email") String email) {
        return graduateService.findGraduateByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/buscar-por-documento")
    public ResponseEntity<Graduate> getGraduateByDocumentNumber(@RequestParam("documento") String documentNumber) {
        return graduateService.findGraduateByDocumentNumberGraduate(documentNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/buscar-por-id")
    public ResponseEntity<Graduate> getGraduateById(@RequestParam("id") Long idGraduate) {
        return graduateService.findGraduateById(idGraduate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Graduate>> getAllGraduates() {
        return ResponseEntity.ok(graduateService.getGraduates());
    }

    @PostMapping("/registrar")
    public ResponseEntity<Graduate> createGraduate(@RequestBody UserRegisterRequest userRegisterRequest) {
        return ResponseEntity.ok(graduateService.createGraduate(userRegisterRequest));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Graduate> updateGraduate(@PathVariable("id") Long Id, @RequestBody UpdateGraduateRequest updateRequest) {
        return ResponseEntity.ok(graduateService.updateGraduate(updateRequest.getGraduateDTO(), updateRequest.getUserDTO()));
    }
}
