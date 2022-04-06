package com.imocha.keycloak.model;

import lombok.Data;

@Data
public class CreateClientProtocolMapperRequest {

  private String name;
  private String protocol;
  private String protocolMapper;
  private ProtocolMapperRequestConfig config;
}
