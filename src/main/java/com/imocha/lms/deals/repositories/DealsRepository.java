package com.imocha.lms.deals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.deals.entities.Deals;

public interface DealsRepository extends JpaRepository<Deals, Long> {

    public List<Deals> findByContextableTypeIgnoreCaseContainingAndContextableId(String contextableType,
            Long contextableId);

}
