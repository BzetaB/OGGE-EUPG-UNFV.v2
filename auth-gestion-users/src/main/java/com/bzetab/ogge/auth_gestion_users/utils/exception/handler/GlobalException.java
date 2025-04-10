package com.bzetab.ogge.auth_gestion_users.utils.exception.handler;

import com.bzetab.ogge.auth_gestion_users.utils.GeneralResponse;
import com.bzetab.ogge.auth_gestion_users.utils.exception.custom.AlreadyExist;
import com.bzetab.ogge.auth_gestion_users.utils.exception.custom.NotEmpty;
import com.bzetab.ogge.auth_gestion_users.utils.exception.custom.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<GeneralResponse> exNotFound(NotFound notFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(GeneralResponse.builder()
                        .message(notFound.getMessage())
                        .build());
    }

    @ExceptionHandler(NotEmpty.class)
    public ResponseEntity<GeneralResponse> exNotEmpty(NotEmpty notEmpty){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(GeneralResponse.builder()
                        .message(notEmpty.getMessage())
                        .build());
    }

    @ExceptionHandler(AlreadyExist.class)
    public ResponseEntity<GeneralResponse> exAlreadyExist(AlreadyExist alreadyExist){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(GeneralResponse.builder()
                        .message(alreadyExist.getMessage())
                        .build());
    }

}
