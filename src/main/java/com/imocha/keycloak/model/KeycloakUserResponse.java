package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class KeycloakUserResponse {

    private String id;

    private Boolean enabled;
}
