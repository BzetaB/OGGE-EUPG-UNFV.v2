package com.bzetab.ogge.auth_gestion_users.model.entities;

import com.bzetab.ogge.auth_gestion_users.model.enums.DocumentType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    private String nameEmployee;
    private String lastNameEmployee;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(unique = true)
    private String documentNumberEmployee;

    @Column(unique = true)
    private String cellphoneEmployee;

    private Boolean activeEmployee;

    private LocalDateTime dateAdmissionEmployee;

    private LocalDateTime departureEmployee;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    //RELATIONS
    @OneToOne
    @JoinColumn(name = "id_user", unique = true, nullable = false)
    @JsonManagedReference("employee_user")
    private Users user;
}
