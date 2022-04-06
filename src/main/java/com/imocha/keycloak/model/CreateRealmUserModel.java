package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class CreateRealmUserModel {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String realmName;
    private String realmRoles;
    private String password;
	
}
