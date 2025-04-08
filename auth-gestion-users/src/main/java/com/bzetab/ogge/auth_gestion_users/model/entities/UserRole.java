package com.bzetab.ogge.auth_gestion_users.model.entities;

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
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserRole;
    private Boolean active;

    @UpdateTimestamp
    LocalDateTime fechaCreacionModificacion;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    @JsonBackReference("role_user")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @JsonBackReference("user_role")
    private Users users;
}
