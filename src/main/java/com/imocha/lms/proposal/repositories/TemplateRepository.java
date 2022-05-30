package com.imocha.lms.proposal.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.imocha.lms.proposal.entities.Template;

public interface TemplateRepository extends JpaRepository<Template,Integer>{
	
	Page<Template> findByTitleContaining(String title, Pageable pageable);
	Page<Template> findAll(Pageable pageable);
	
	//Template save(Template template);

}
