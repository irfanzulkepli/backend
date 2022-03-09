package com.imocha.lms.pipelines.repository;

import com.imocha.lms.pipelines.entities.Pipelines;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PipelinesRepository extends JpaRepository<Pipelines, Long> {

}
