package com.imocha.lms.deals.pipelines.repository;

import java.util.List;
import java.util.Optional;

import com.imocha.lms.deals.pipelines.entities.Pipelines;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PipelinesRepository extends JpaRepository<Pipelines, Long> {

    public List<Pipelines> findByActiveTrue();

}
