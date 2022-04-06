package com.imocha.keycloak.model;

import java.util.List;

import lombok.Data;

@Data
public class CreateRealmClientRequest {

    private String clientId;
    private String name;
    private Boolean directAccessGrantsEnabled;
    private Boolean enabled;
    private String protocol;
    private String rootUrl;
    private List<String> redirectUris;
    private Boolean standardFlowEnabled;
    private Boolean serviceAccountsEnabled;
    private Boolean publicClient;
    private Boolean bearerOnly;
    private List<String> webOrigins;
}
