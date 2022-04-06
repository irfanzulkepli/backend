package com.imocha.keycloak.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.collect.Lists;
import com.imocha.keycloak.model.ClientLoginRequest;
import com.imocha.keycloak.model.ClientSecretResponse;
import com.imocha.keycloak.model.CreateClientProtocolMapperRequest;
import com.imocha.keycloak.model.CreateRealmClientRequest;
import com.imocha.keycloak.model.CreateRealmRequest;
import com.imocha.keycloak.model.CreateRealmRoleComposites;
import com.imocha.keycloak.model.CreateRealmRoles;
import com.imocha.keycloak.model.CreateRealmUserModel;
import com.imocha.keycloak.model.CreateRealmUserRequest;
import com.imocha.keycloak.model.CreateUserModel;
import com.imocha.keycloak.model.GetClientRoleResponse;
import com.imocha.keycloak.model.GetKeycloakUserRequest;
import com.imocha.keycloak.model.GetRealmClientResponse;
import com.imocha.keycloak.model.KeycloakUserResponse;
import com.imocha.keycloak.model.RealmAccessResponse;
import com.imocha.keycloak.model.RealmRole;
import com.imocha.keycloak.model.RealmRoleRequest;
import com.imocha.keycloak.model.RealmRoleResponse;
import com.imocha.keycloak.model.SoftDeleteKeycloakUser;
import com.imocha.keycloak.model.Token;
import com.imocha.keycloak.model.UpdateRealmModel;
import com.imocha.keycloak.model.UpdateRealmRequest;
import com.imocha.keycloak.model.UpdateUserPasswordModel;
import com.imocha.keycloak.model.UpdateUserPasswordRequest;
import com.imocha.keycloak.model.UpdateUserRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeycloakService {

	@Value("${keycloak.url}")
	private String keycloakUrl;

	@Value("${keycloak.admin_url}")
	private String keycloakAdminUrl;

	@Value("${keycloak.client_secret}")
	private String keycloakClientSecret;

	@Value("${keycloak.admin_username}")
	private String keycloakAdminUsername;

	@Value("${keycloak.admin_password}")
	private String keycloakAdminPassword;

	@Value("${keycloak.realm_name}")
	private String keycloakRealmName;

	@Value("${keycloak.grant_type}")
	private String keycloakGrantType;

	@Value("${keycloak.client_id}")
	private String keycloakClientId;

	@Autowired
	private WebClient.Builder webClientBuilder;

	public String getKeycloakAdminToken() {
		Token adminToken = webClientBuilder.build().post().uri(keycloakUrl + "master/protocol/openid-connect/token")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.body(BodyInserters.fromFormData("grant_type", "password").with("client_id", "admin-cli")
						.with("username", keycloakAdminUsername).with("password", keycloakAdminPassword)
						.with("client_secret", keycloakClientSecret))
				.retrieve().bodyToMono(Token.class).block();
		return "Bearer " + adminToken.getAccess_token();
	}

	public String createRealm(CreateRealmRequest payload, String token) {
		String response = webClientBuilder.build().post().uri(keycloakAdminUrl)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(payload).retrieve().bodyToMono(String.class)
				.onErrorReturn("Error occurred when creating realm.").block();
		return response;
	}

	public String createRealmUser(CreateRealmUserRequest payload, String companyId, String token) {
		String response = webClientBuilder.build().post().uri(keycloakAdminUrl + companyId + "/users")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(payload).retrieve().bodyToMono(String.class)
				.onErrorReturn("Error occurred when creating realm user.").block();
		System.out.println("response " + response);
		return response;
	}

	public String createRealmUserByClient(CreateUserModel payload, String companyId, String token) {
		System.out.println("URI " + keycloakAdminUrl + companyId + "/users");
		String response = webClientBuilder.build().post().uri(keycloakAdminUrl + companyId + "/users")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(payload).retrieve().bodyToMono(String.class)
				.block();
		return response;
	}

	public String createRealmClient(String clientId, CreateRealmClientRequest payload, String token) {
		String response = webClientBuilder.build().post().uri(keycloakAdminUrl + clientId + "/clients")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(payload).retrieve().bodyToMono(String.class)
				.onErrorReturn("Error occurred when creating realm client.").block();
		return response;
	}

	public String getRealmClientId(String realmName, String clientName, String token) {
		List<GetRealmClientResponse> getRealmClientResponse = webClientBuilder.build().get()
				.uri(keycloakAdminUrl + realmName + "/clients?clientId=" + clientName)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<GetRealmClientResponse>>() {
				}).block();
		return getRealmClientResponse.get(0).getId();
	}

	public String getRealmUserId(String realmName, String email, String token) throws UnsupportedEncodingException {
		String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8.toString());
		List<GetRealmClientResponse> getRealmClientResponse = webClientBuilder.build().get()
				.uri(URI.create(keycloakAdminUrl + realmName + "/users?email=" + encodedEmail))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<GetRealmClientResponse>>() {
				}).block();
		return getRealmClientResponse.get(0).getId();
	}

	public String createClientProtocolMapper(String realmName, String clientName,
			CreateClientProtocolMapperRequest payload, String token) {
		String clientId = getRealmClientId(realmName, clientName, token);
		String response = webClientBuilder.build().post()
				.uri(keycloakAdminUrl + realmName + "/clients/" + clientId + "/protocol-mappers/models")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(payload).retrieve().bodyToMono(String.class)
				.onErrorReturn("Error occurred when creating realm client protocol mapper.").block();
		return response;
	}

	public ClientSecretResponse getClientSecret(ClientLoginRequest clientLoginRequest, String token) {
		String clientId = getRealmClientId(clientLoginRequest.getRealmName(), clientLoginRequest.getClientId(), token);
		ClientSecretResponse clientSecret = webClientBuilder.build().get()
				.uri(keycloakAdminUrl + clientLoginRequest.getRealmName() + "/clients/" + clientId + "/client-secret")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).retrieve().bodyToMono(ClientSecretResponse.class).block();
		return clientSecret;
	}

	public void addRealmRoles(String realmName, CreateRealmRoles createRealmRoles, String token) {
		webClientBuilder.build().post().uri(keycloakAdminUrl + realmName + "/roles")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(createRealmRoles).retrieve()
				.bodyToMono(String.class).block();
	}

	public List<GetClientRoleResponse> getClientRoles(String realmName, String token) {
		List<GetClientRoleResponse> clientRoles = webClientBuilder.build().get()
				.uri(keycloakAdminUrl + realmName + "/roles")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<GetClientRoleResponse>>() {
				}).block();
		return clientRoles;
	}

	public List<RealmAccessResponse> getAllRealmManagementRoles(String realmName, String token) {
		String realmManagementId = getRealmClientId(realmName, "realm-management", token);
		List<RealmAccessResponse> realmManagementList = webClientBuilder.build().get()
				.uri(keycloakAdminUrl + realmName + "/clients/" + realmManagementId + "/roles")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RealmAccessResponse>>() {
				}).block();
		return realmManagementList;
	}

	public List<RealmRoleResponse> getUserRealmRole(String realmName, String token, String userId) {
		List<RealmRoleResponse> userRoles = webClientBuilder.build().get()
				.uri(keycloakAdminUrl + realmName + "/users/" + userId + "/role-mappings/realm")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RealmRoleResponse>>() {
				}).block();
		return userRoles;
	}

	public void addRealmRolesComposite(RealmRole realmRole, String token) {
		String realmName = realmRole.getRealmName();
		List<String> realmRoles = realmRole.getRealmRoles();
		String roleName = realmRole.getRoleName();

		String realmManagementId = getRealmClientId(realmName, "realm-management", token);

		List<RealmAccessResponse> realmAccessList = getAllRealmManagementRoles(realmName, token);
		List<RealmAccessResponse> realmAccessLists = Lists.newArrayList(realmAccessList);

		realmAccessLists.stream().filter(realmAccessResponse -> realmRoles.contains(realmAccessResponse.getName()))
				.map(realmAccess -> {
					CreateRealmRoleComposites createCompositeRole = new CreateRealmRoleComposites();
					createCompositeRole.setId(realmAccess.getId());
					createCompositeRole.setName(realmAccess.getName());
					createCompositeRole.setContainerId(realmManagementId);
					return Lists.newArrayList(createCompositeRole);
				}).forEach(e -> {
					webClientBuilder.build().post()
							.uri(keycloakAdminUrl + realmName + "/roles/" + roleName + "/composites")
							.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
							.header(HttpHeaders.AUTHORIZATION, token).bodyValue(e).retrieve().bodyToMono(String.class)
							.block();
				});
	}

	public void assignRoleToUser(String realmName, String email, List<RealmRoleRequest> payload, String token)
			throws UnsupportedEncodingException {
		String userId = getRealmUserId(realmName, email, token);
		webClientBuilder.build().post().uri(keycloakAdminUrl + realmName + "/users/" + userId + "/role-mappings/realm")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(payload).retrieve().bodyToMono(String.class)
				.block();
	}

	public void unassignRoleToUser(String realmName, String email, List<RealmRoleRequest> payload, String token)
			throws UnsupportedEncodingException {
		String userId = getRealmUserId(realmName, email, token);
		webClientBuilder.build().method(HttpMethod.DELETE)
				.uri(keycloakAdminUrl + realmName + "/users/" + userId + "/role-mappings/realm")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(payload).retrieve().bodyToMono(String.class)
				.block();
	}

	public String getRealmRoleIdByRealmName(String realmName, String roleName, String token) {
		List<GetClientRoleResponse> clientRoles = getClientRoles(realmName, token);
		for (GetClientRoleResponse item : clientRoles) {
			if (item.getName().equals(roleName)) {
				return item.getId();
			}
		}
		return "No role found.";
	}

	public void sendRequiredActionEmail(String realmName, String userId, List<String> requiredActions, String token) {
		webClientBuilder.build().put().uri(keycloakAdminUrl + realmName + "/users/" + userId + "/execute-actions-email")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(requiredActions).retrieve().bodyToMono(String.class)
				.block();
	}

	public void softDeleteKeycloakUser(String realmName, String userId, String token) {
		SoftDeleteKeycloakUser user = new SoftDeleteKeycloakUser();
		user.setEnabled(false);
		webClientBuilder.build().put().uri(keycloakAdminUrl + realmName + "/users/" + userId)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(user).retrieve().bodyToMono(String.class).block();
	}

	public void reactivateKeycloakUser(String realmName, String userId, String token) {
		SoftDeleteKeycloakUser user = new SoftDeleteKeycloakUser();
		user.setEnabled(true);
		webClientBuilder.build().put().uri(keycloakAdminUrl + realmName + "/users/" + userId)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(user).retrieve().bodyToMono(String.class).block();
	}

	public void updateKeycloakUser(UpdateUserRequest payload, String realmName, String userId, String token) {
		webClientBuilder.build().put().uri(keycloakAdminUrl + realmName + "/users/" + userId)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(payload).retrieve().bodyToMono(String.class)
				.block();
	}

	public void setupUserPassword(UpdateUserPasswordModel payload, String token) {
		UpdateUserPasswordRequest updatePasswordRequest = new UpdateUserPasswordRequest();
		updatePasswordRequest.setType(payload.getType());
		updatePasswordRequest.setValue(payload.getValue());

		webClientBuilder.build().put()
				.uri(keycloakAdminUrl + payload.getRealmName() + "/users/" + payload.getUserId() + "/reset-password")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(updatePasswordRequest).retrieve()
				.bodyToMono(String.class).block();
	}

	public Boolean getKeycloakUserActivation(GetKeycloakUserRequest payload, String token) {
		KeycloakUserResponse userInfo = webClientBuilder.build().get()
				.uri(keycloakAdminUrl + payload.getRealmName() + "/users/" + payload.getUserId())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).retrieve().bodyToMono(KeycloakUserResponse.class).block();
		System.out.println(userInfo);
		return userInfo.getEnabled();
	}

	public void updateRealm(UpdateRealmModel payload, String token) {
		UpdateRealmRequest request = new UpdateRealmRequest();
		request.setRealm(payload.getRealm());
		// request.setSmtpServer(payload.getSmtpServer());

		webClientBuilder.build().put().uri(keycloakAdminUrl + payload.getOldRealm())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.header(HttpHeaders.AUTHORIZATION, token).bodyValue(request).retrieve().bodyToMono(String.class)
				.block();
	}

	public String createRealmUser(CreateRealmUserModel payload) {

		Keycloak keycloak = KeycloakBuilder.builder().serverUrl(keycloakUrl).realm(keycloakRealmName)
				.grantType(keycloakGrantType).username(keycloakAdminUsername).password(keycloakAdminPassword)
				.clientId(keycloakClientId).build();

		UserRepresentation userRepresentation = new UserRepresentation();

		userRepresentation.setEmail(payload.getEmail());
		userRepresentation.setFirstName(payload.getFirstName());
		userRepresentation.setLastName(payload.getLastName());
		userRepresentation.setUsername(payload.getUsername());
		userRepresentation.setEmail(payload.getEmail());
		userRepresentation.setEnabled(true);

		RealmResource realmResource = keycloak.realm(keycloakRealmName);
		UsersResource userRessource = realmResource.users();

		Response response = userRessource.create(userRepresentation);
		String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

		log.info("Response: " + response);
		log.info("Location: " + response.getLocation());
		log.info("Path: " + response.getLocation().getPath());
		log.info("User created with userId: %s%n" + userId);

		CredentialRepresentation passwordCred = new CredentialRepresentation();
		passwordCred.setTemporary(false);
		passwordCred.setType(CredentialRepresentation.PASSWORD);
		passwordCred.setValue(payload.getPassword());

		userRessource.get(userId).resetPassword(passwordCred);

		return userId;

	}
}
