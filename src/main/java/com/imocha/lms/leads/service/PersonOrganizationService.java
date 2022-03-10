package com.imocha.lms.leads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.leads.entities.PersonOrganization;
import com.imocha.lms.leads.repositories.PersonOrganizationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonOrganizationService {

	@Autowired
	private PersonOrganizationRepository personOrganizationRepository;

	List<PersonOrganization> findByPeopleId(long peopleId) {
		return personOrganizationRepository.findByPeopleId(peopleId);
	}

	public void delete(Long id) {
		List<PersonOrganization> personOrganizations = personOrganizationRepository.findByPeopleId(id);
		personOrganizations.forEach(personOrganization -> {
			personOrganizationRepository.delete(personOrganization);
		});
	}

	public void save(PersonOrganization personOrganization) {
		personOrganizationRepository.save(personOrganization);
	}
}
