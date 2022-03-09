package com.imocha.lms.lostReasons.repository;

import com.imocha.lms.lostReasons.entities.LostReasons;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LostReasonsRepository extends JpaRepository<LostReasons, Long> {
    
}
