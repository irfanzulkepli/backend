package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class GetClientRoleResponse {

    private String id;

    private String name;

    private String description;

    private String containerId;

    private Boolean composite;

    private Boolean clientRole;

}
