package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class GetKeycloakUserRequest {

    private String realmName;

    private String userId;
}
