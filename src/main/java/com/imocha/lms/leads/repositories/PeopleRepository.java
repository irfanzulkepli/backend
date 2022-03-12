package com.imocha.lms.leads.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.leads.entities.People;

public interface PeopleRepository extends JpaRepository<People, Long> {
	Page<People> findByActiveTrue(Pageable pageable);

	List<People> findByActiveTrue();

	List<People> findByNameIgnoreCaseContaining(String name);
}