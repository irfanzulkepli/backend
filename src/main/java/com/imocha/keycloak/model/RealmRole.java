package com.imocha.keycloak.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RealmRole {

    private String realmName;
    private List<String> realmRoles;
    private String roleName;

}
