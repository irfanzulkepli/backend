package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class UpdateUserRequest {

    private String email;

    private String firstName;

    private String lastName;

//    private String username;

}
