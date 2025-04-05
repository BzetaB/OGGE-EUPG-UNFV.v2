package com.bzetab.ogge.auth_gestion_users.model.entities;

import com.bzetab.ogge.auth_gestion_users.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(unique = true)
    private String emailUser;
    private String passwordUser;
    private Boolean statusUser;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //RELATIONS
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference("graduate_user")
    private Graduate graduate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference("employee_user")
    private Employee employee;
}
