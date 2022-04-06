package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class UpdateUserPasswordModel {

    private String type;

    private String value;

    private Boolean temporary = false;

    private String realmName;

    private String userId;

    private String actionId;

}
