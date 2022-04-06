package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class ClientLoginRequest {

    private String grantType;

    private String clientId;

    private String clientSecret;

    private String username;

    private String password;

    private String realmName;
}
