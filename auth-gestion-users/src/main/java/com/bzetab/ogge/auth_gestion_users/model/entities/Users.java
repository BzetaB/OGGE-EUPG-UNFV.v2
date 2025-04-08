package com.bzetab.ogge.auth_gestion_users.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(unique = true)
    private String emailUser;
    private String passwordUser;
    private Boolean statusUser;

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

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user_role")
    private Set<UserRole> role = new HashSet<>();
}
