package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class CreateRealmRoles {

    private String name;

    private Boolean composite = false;

    private Boolean clientRole = false;

    private String containerId;
}
