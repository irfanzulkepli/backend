package com.imocha.lms.leads.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.entities.PersonOrganization;
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
}
