package com.imocha.lms.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.imocha.keycloak.model.CreateRealmUserModel;
import com.imocha.keycloak.service.KeycloakService;
import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.common.service.StatusesService;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.model.UsersListResponse;
import com.imocha.lms.users.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private KeycloakService keycloakService;

	@Autowired
	private StatusesService statusesService;

	public List<UsersListResponse> list() {
		List<Users> list = usersRepository.findAll();
		List<UsersListResponse> response = new ArrayList<UsersListResponse>();
		for (Users users : list) {
			UsersListResponse usersListResponse = this.mapUsersToUsersListResponse(users);
			response.add(usersListResponse);
		}
		return response;
	}

	private UsersListResponse mapUsersToUsersListResponse(Users users) {
		UsersListResponse response = new UsersListResponse();
		BeanUtils.copyProperties(users, response);
		return response;
	}

	public Users get(long id) {
		Optional<Users> uOptional = usersRepository.findById(id);
		uOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

		return uOptional.get();
	}

	public Users createUsers(CreateRealmUserModel payload) {
		String keycloakId = keycloakService.createRealmUser(payload);
		Users user = new Users();

		BeanUtils.copyProperties(payload, user);
		Statuses status = statusesService.findById((long) 1);

		user.setStatuses(status);
		user.setKeycloakId(keycloakId);

		return usersRepository.save(user);
	}

	public Users getByToken() {
		String keycloakId = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("keycloak Id: " + keycloakId);

		Users user = usersRepository.findByKeycloakId(keycloakId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

		return user;
	}
	
	public void logout() {
		
	}
}
