package com.imocha.lms.leads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.imocha.lms.leads.entities.ContactTypes;

public interface ContactTypesRepositories extends JpaRepository<ContactTypes, Long> {
    List<ContactTypes> findByNameContaining(String name);
}
