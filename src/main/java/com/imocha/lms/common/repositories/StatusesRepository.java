package com.imocha.lms.common.repositories;

import com.imocha.lms.common.entities.Statuses;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusesRepository extends JpaRepository<Statuses, Long> {

    public Statuses findByName(String name);

}
