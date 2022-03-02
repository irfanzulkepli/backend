package com.imocha.lms.leads.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.leads.entities.People;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
