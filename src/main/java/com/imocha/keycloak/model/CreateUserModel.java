package com.imocha.keycloak.model;

import java.util.List;

import lombok.Data;

@Data
public class CreateUserModel {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private List<String> realmRoles;

    private Boolean enabled = true;
}
