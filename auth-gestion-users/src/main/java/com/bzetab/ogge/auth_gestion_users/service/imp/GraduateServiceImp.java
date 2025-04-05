package com.bzetab.ogge.auth_gestion_users.service.imp;

import com.bzetab.ogge.auth_gestion_users.model.dto.GraduateDTO;
import com.bzetab.ogge.auth_gestion_users.model.dto.UserDTO;
import com.bzetab.ogge.auth_gestion_users.model.entities.Graduate;
import com.bzetab.ogge.auth_gestion_users.model.entities.User;
import com.bzetab.ogge.auth_gestion_users.model.enums.Role;
import com.bzetab.ogge.auth_gestion_users.model.request.UserRegisterRequest;
import com.bzetab.ogge.auth_gestion_users.repository.GraduateRepository;
import com.bzetab.ogge.auth_gestion_users.repository.UserRepository;
import com.bzetab.ogge.auth_gestion_users.service.GraduateService;
import com.bzetab.ogge.auth_gestion_users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Transactional()
    @Override
    public Graduate createGraduate(UserRegisterRequest userRegisterRequest) {

        if (graduateRepository.findGraduateByCellphoneGraduate(userRegisterRequest.getCellphone()).isPresent()) {
            throw new RuntimeException("Graduate Cellphone already exists");
        }

        if(graduateRepository.findGraduateByDocumentNumberGraduate(userRegisterRequest.getDocumentNumber()).isPresent()) {
            throw new RuntimeException("Graduate Document number already exists");
        }

        User userGraduate = userService.createUser(userRegisterRequest);

        Graduate graduate = Graduate.builder()
                .nameGraduate(userRegisterRequest.getName())
                .lastNameGraduate(userRegisterRequest.getLastName())
                .documentType(userRegisterRequest.getDocumentType())
                .documentNumberGraduate(userRegisterRequest.getDocumentNumber())
                .cellphoneGraduate(userRegisterRequest.getCellphone())
                .activeGraduate(true)
                .createdAt(LocalDateTime.now())
                .currentDegree(userRegisterRequest.getCurrentDegree())
                .aspireDegree(userRegisterRequest.getAspireDegree())
                .user(userGraduate)
                .build();
        return graduateRepository.save(graduate);
    }

    @Override
    public List<Graduate> getGraduates() {
        return graduateRepository.findAll();
    }

    @Transactional()
    @Override
    public Graduate updateGraduate(GraduateDTO graduateDTO, UserDTO userDTO) {
        Graduate updateGraduate = graduateRepository.findById(graduateDTO.getIdGraduate())
                .orElseThrow(() -> new RuntimeException("Graduate not found"));

        User user = updateGraduate.getUser();

        if(userDTO != null) {
            if(!updateGraduate.getUser().getIdUser().equals(userDTO.getId())){
                throw new RuntimeException("Graduate user id does not match");
            }
            userService.updateUser(userDTO, user);
        }

        if(graduateDTO.getName() != null) {
            updateGraduate.setNameGraduate(graduateDTO.getName());
        }
        if(graduateDTO.getLastName() != null) {
            updateGraduate.setLastNameGraduate(graduateDTO.getLastName());
        }
        if(graduateDTO.getDocumentType() != null) {
            updateGraduate.setDocumentType(graduateDTO.getDocumentType());
        }
        if(graduateDTO.getDocumentNumber() != null && !graduateDTO.getDocumentNumber().equals(updateGraduate.getDocumentNumberGraduate())) {
            if(this.findGraduateByDocumentNumberGraduate(graduateDTO.getDocumentNumber()).isPresent()) {
                throw new RuntimeException("Graduate Document number already exists");
            }
            updateGraduate.setDocumentNumberGraduate(graduateDTO.getDocumentNumber());

        }
        if(graduateDTO.getCellphone() != null && !graduateDTO.getCellphone().equals(updateGraduate.getCellphoneGraduate())) {
            if(graduateRepository.findGraduateByCellphoneGraduate(graduateDTO.getCellphone()).isPresent()) {
                throw new RuntimeException("Graduate Cellphone already exists");
            }
            updateGraduate.setCellphoneGraduate(graduateDTO.getCellphone());
        }
        if (graduateDTO.getActive() != null) {
            updateGraduate.setActiveGraduate(graduateDTO.getActive());
        }
        if (graduateDTO.getCurrentDegree() != null) {
            updateGraduate.setCurrentDegree(graduateDTO.getCurrentDegree());
        }
        if (graduateDTO.getAspiredDegree() != null) {
            updateGraduate.setAspireDegree(graduateDTO.getAspiredDegree());
        }

        return graduateRepository.save(updateGraduate);
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
