package com.imocha.lms.deals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.deals.entities.LostReasons;

public interface LostReasonsRepository extends JpaRepository<LostReasons, Long> {

}
