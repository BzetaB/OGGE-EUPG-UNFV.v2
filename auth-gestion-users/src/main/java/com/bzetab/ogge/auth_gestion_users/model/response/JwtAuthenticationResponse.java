package com.bzetab.ogge.auth_gestion_users.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private List<String> roles;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
