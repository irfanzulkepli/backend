package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class Credentials {

  private String type;
  private String value;
  private Boolean temporary = false;
}
