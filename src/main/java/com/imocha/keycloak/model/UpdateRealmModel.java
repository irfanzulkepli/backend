package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class UpdateRealmModel {
    private String oldRealm;
    private String realm;
//    private SmtpModel smtpServer;
}
