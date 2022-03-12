package com.imocha.lms.leads.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.leads.entities.Organizations;

public interface OrganizationRepository extends JpaRepository<Organizations, Long> {

	Page<Organizations> findByActiveTrue(Pageable pageable);

	List<Organizations> findByActiveTrue();
}
