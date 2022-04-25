package com.imocha.lms.deals.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imocha.lms.entities.Proposals;

@Repository
public interface ProposalsRepository extends JpaRepository<Proposals, Long> {

}
