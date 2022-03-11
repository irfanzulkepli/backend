package com.imocha.lms.leads.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imocha.lms.leads.entities.Organizations;
import com.imocha.lms.leads.repositories.OrganizationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;

	public Organizations getOrganizationById(Long id) {
		Optional<Organizations> org = organizationRepository.findById(id);

		if (org.isPresent()) {
			Organizations organization = org.get();
			return organization;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

	}
}
