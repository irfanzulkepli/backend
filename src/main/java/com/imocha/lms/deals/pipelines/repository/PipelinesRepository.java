package com.imocha.lms.deals.pipelines.repository;

import java.util.List;

import com.imocha.lms.deals.pipelines.entities.Pipelines;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PipelinesRepository extends JpaRepository<Pipelines, Long> {

	public Page<Pipelines> findAll(Specification<Pipelines> spec, Pageable pageable);

    public List<Pipelines> findByActiveTrue();

}
