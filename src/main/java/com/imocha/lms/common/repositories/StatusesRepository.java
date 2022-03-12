package com.imocha.lms.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.common.entities.Statuses;

public interface StatusesRepository extends JpaRepository<Statuses, Long> {

}
