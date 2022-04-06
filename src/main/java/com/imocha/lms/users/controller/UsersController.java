package com.imocha.lms.users.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imocha.keycloak.model.CreateRealmUserModel;
import com.imocha.keycloak.model.CreateUserModel;
import com.imocha.keycloak.service.KeycloakService;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.model.UsersListResponse;
import com.imocha.lms.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping("list")
	public List<UsersListResponse> list() {
		return usersService.list();
	}

	@GetMapping("list-v2")
	public List<UsersListResponse> listv1(@RequestBody String name) {
		return usersService.list();
	}

	@GetMapping("details")
	public Users getByToken() {
		return usersService.getByToken();
	}

	@PostMapping("/create")
	public Users create(@RequestBody CreateRealmUserModel payload,
			@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		return usersService.createUsers(payload);
	}
}
