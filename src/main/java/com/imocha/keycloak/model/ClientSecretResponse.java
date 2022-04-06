package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class ClientSecretResponse {

    private String type;

    private String value;
}
