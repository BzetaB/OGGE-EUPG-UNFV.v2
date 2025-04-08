package com.bzetab.ogge.auth_gestion_users.model.entities;

import com.bzetab.ogge.auth_gestion_users.model.enums.Degree;
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
@Table(name = "Graduate")
public class Graduate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGraduate;

    private String nameGraduate;
    private String lastNameGraduate;

    @Column(unique = true)
    private String documentNumberGraduate;

    @Column(unique = true)
    private String cellphoneGraduate;

    private Boolean activeGraduate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Enumerated(EnumType.STRING)
    private Degree currentDegree;

    @Enumerated(EnumType.STRING)
    private Degree aspireDegree;

    //RELATIONS
    @OneToOne
    @JoinColumn(name = "id_user", unique = true, nullable = false)
    @JsonManagedReference("graduate_user")
    private Users user;

}
