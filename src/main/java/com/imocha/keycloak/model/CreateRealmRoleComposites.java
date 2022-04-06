package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class CreateRealmRoleComposites {

    private String id;

    private String name;

    private String containerId;

    private Boolean composite = true;

    private Boolean clientRole = true;
}
