package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class UpdateRealmRequest {
    private String realm;
    private SmtpModel smtpServer;
}
