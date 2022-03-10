package com.imocha.lms.leads.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.leads.entities.PersonOrganization;

public interface PersonOrganizationRepository extends JpaRepository<PersonOrganization, Long> {

	List<PersonOrganization> findByPeopleId(Long peopleId);

	Long deleteByPeopleId(Long peopleId);
}
