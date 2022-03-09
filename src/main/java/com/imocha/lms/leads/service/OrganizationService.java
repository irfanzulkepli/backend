package com.imocha.lms.leads.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.entities.Organizations;
import com.imocha.lms.leads.repositories.OrganizationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrganizationService {

	@Autowired
	OrganizationRepository organizationRepository;

	public Organizations listOrganization() {
		long id = 1;
		Optional<Organizations> org = organizationRepository.findById(id);

		Organizations organization = org.get();

//		List<PersonOrganization> orgnaztions = organization.getPersons().stream().map(organizationPerson -> {
//			System.out.println("organizationPerson: " + organizationPerson.getPeople());
//
//			return organizationPerson;
//		}).collect(Collectors.toList());

		return organization;
	}
}
