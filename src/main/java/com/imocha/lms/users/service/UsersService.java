package com.imocha.lms.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.model.UsersListResponse;
import com.imocha.lms.users.repository.UsersRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

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
}
