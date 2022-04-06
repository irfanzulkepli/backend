package com.imocha.keycloak.model;

import java.util.List;

import lombok.Data;

@Data
public class CreateRealmUserRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled = true;
    private List<Credentials> credentials;
}
