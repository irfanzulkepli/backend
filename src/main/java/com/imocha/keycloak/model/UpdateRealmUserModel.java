package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class UpdateRealmUserModel {

    private String realmName;

    private String userId;

    private String email;

    private String firstName;

    private String lastName;

    private String contact;

    private String role;
}
