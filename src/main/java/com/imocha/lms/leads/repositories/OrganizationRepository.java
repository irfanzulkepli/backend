package com.imocha.lms.leads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.leads.entities.Organizations;

public interface OrganizationRepository extends JpaRepository<Organizations, Long> {

}
