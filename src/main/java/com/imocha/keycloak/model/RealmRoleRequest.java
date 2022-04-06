package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class RealmRoleRequest {

    private String name;

    private Boolean composite;

    private Boolean clientRole;

    private String Id;
}
