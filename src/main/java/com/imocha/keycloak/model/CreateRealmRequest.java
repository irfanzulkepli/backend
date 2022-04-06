package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class CreateRealmRequest {

  private String id;
  private String realm;
  private SmtpModel smtpServer; 
  private Boolean enabled = true;
}
