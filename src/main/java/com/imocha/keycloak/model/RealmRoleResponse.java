package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class RealmRoleResponse {

    private String id;

    private String name;

    private Boolean composite;

    private Boolean clientRole;

    private String containerId;
}
