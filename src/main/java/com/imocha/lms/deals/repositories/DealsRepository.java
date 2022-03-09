package com.imocha.lms.deals.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.pipelines.entities.Pipelines;

public interface DealsRepository extends JpaRepository<Deals, Long> {

    public List<Deals> findByContextableTypeIgnoreCaseContainingAndContextableId(String contextableType,
            Long contextableId);

    public List<Deals> findByPipelines(Pipelines pipelines);

}
