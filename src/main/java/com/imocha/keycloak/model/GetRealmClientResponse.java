package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class GetRealmClientResponse {

  private String id;
  private String clientId;
  private String name;
}
