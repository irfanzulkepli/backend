package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class RealmAccessResponse {

    private String id;

    private String name;

    private String description;

    private Boolean composite;

    private Boolean clientRole;

    private String containerId;
}
