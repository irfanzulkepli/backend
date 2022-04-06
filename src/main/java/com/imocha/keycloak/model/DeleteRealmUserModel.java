package com.imocha.keycloak.model;

import java.util.List;

import lombok.Data;

@Data
public class DeleteRealmUserModel {

    private List<String> userId;

    private String realmName;
}
