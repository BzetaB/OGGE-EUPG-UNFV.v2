package com.bzetab.ogge.auth_gestion_users.service.imp;

import com.bzetab.ogge.auth_gestion_users.model.dto.GraduateDTO;
import com.bzetab.ogge.auth_gestion_users.model.entities.Graduate;
import com.bzetab.ogge.auth_gestion_users.model.entities.Users;
import com.bzetab.ogge.auth_gestion_users.model.enums.Degree;
import com.bzetab.ogge.auth_gestion_users.model.enums.DocumentType;
import com.bzetab.ogge.auth_gestion_users.model.request.GraduateRegisterRequest;
import com.bzetab.ogge.auth_gestion_users.repository.GraduateRepository;
import com.bzetab.ogge.auth_gestion_users.service.GraduateService;
import com.bzetab.ogge.auth_gestion_users.service.UserService;
import com.bzetab.ogge.auth_gestion_users.utils.GeneralResponse;
import com.bzetab.ogge.auth_gestion_users.utils.exception.custom.AlreadyExist;
import com.bzetab.ogge.auth_gestion_users.utils.exception.custom.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GraduateServiceImp  implements GraduateService {

    private final GraduateRepository graduateRepository;
    private final UserService userService;

    public GraduateServiceImp(GraduateRepository graduateRepository, UserService userService) {
        this.graduateRepository = graduateRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public Graduate createEgresado(GraduateRegisterRequest request) {
        if(request.getDocumentNumber().isEmpty()){
            throw new NotEmpty("El documento no puede estar vacio");
        }
        if(graduateRepository.findGraduateByDocumentNumberGraduate(request.getDocumentNumber()).isPresent()){
            throw new AlreadyExist("El documento ya existe");
        }
        if(request.getCellphone().isEmpty()){
            throw new NotEmpty("El cellphone no puede estar vacio");
        }
        if(graduateRepository.findGraduateByCellphoneGraduate(request.getCellphone()).isPresent()){
            throw new AlreadyExist("El cellphone ya existe");
        }
        List<String> rol = List.of("EGRESADO");
        Users user = userService.createUser(request.getEmail(), request.getPassword(), rol);

        Graduate graduate = Graduate.builder()
                .nameGraduate(request.getName())
                .lastNameGraduate(request.getLastName())
                .documentType(DocumentType.valueOf(request.getDocumentType()))
                .documentNumberGraduate(request.getDocumentNumber())
                .cellphoneGraduate(request.getCellphone())
                .activeGraduate(true)
                .currentDegree(Degree.valueOf(request.getCurrentDegree()))
                .aspireDegree(Degree.valueOf(request.getAspireDegree()))
                .user(user)
                .build();
        return graduateRepository.save(graduate);
    }

    @Override
    public GeneralResponse generateGraduateDTO(Graduate graduate) {
        return GeneralResponse.builder()
                .message("Egresado registrado exitosamente")
                .data(GraduateDTO.builder()
                        .name(graduate.getNameGraduate())
                        .lastName(graduate.getLastNameGraduate())
                        .email(graduate.getUser().getEmailUser())
                        .documentType(graduate.getDocumentType().name())
                        .documentNumber(graduate.getDocumentNumberGraduate())
                        .cellphone(graduate.getCellphoneGraduate())
                        .currentDegree(graduate.getCurrentDegree().name())
                        .aspireDegree(graduate.getAspireDegree().name())
                        .build())
                .build();
    }


    @Override
    public List<Graduate> getGraduates() {
        return graduateRepository.findAll();
    }


    @Override
    public Optional<Graduate> findGraduateById(Long id) {
        return graduateRepository.findById(id);
    }

    @Override
    public Optional<Graduate> findGraduateByDocumentNumberGraduate(String documentNumber) {
        return graduateRepository.findGraduateByDocumentNumberGraduate(documentNumber);
    }

    @Override
    public Optional<Graduate> findGraduateByEmail(String email) {
        return graduateRepository.findGraduateByUser_EmailUser(email);
    }

}
