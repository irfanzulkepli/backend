package com.imocha.lms.deals.repositories;

import com.imocha.lms.deals.entities.LostReasons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostReasonsRepository extends JpaRepository<LostReasons, Long> {

    public Page<LostReasons> findAll(Specification<LostReasons> spec, Pageable pageable);

}
