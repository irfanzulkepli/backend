package com.imocha.keycloak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProtocolMapperRequestConfig {

  @JsonProperty("claim.value")
  private String claimValue;

  @JsonProperty("userinfo.token.claim")
  private String userinfoTokenClaim;

  @JsonProperty("id.token.claim")
  private String idTokenClaim;

  @JsonProperty("access.token.claim")
  private String accessTokenClaim;

  @JsonProperty("claim.name")
  private String claimName;

  @JsonProperty("jsonType.label")
  private String jsonTypeLabel;
}
