package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class UpdateUserPasswordRequest {

    private String type;

    private String value;

    private Boolean temporary = false;
}
