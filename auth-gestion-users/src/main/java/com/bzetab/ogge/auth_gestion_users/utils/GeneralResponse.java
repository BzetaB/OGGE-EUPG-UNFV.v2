package com.bzetab.ogge.auth_gestion_users.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse {
    private String message;
    private Object data;
}
