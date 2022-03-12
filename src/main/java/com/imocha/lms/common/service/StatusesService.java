package com.imocha.lms.common.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.common.repositories.StatusesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StatusesService {

	@Autowired
	StatusesRepository statusesRepository;

	public Statuses getById(Long id) {
		return statusesRepository.getById(id);
	}

	public Statuses findById(Long id) {
		Optional<Statuses> statusOptional = statusesRepository.findById(id);

		if (statusOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
		return statusOptional.get();
	}
}
