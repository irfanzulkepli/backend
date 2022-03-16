package com.imocha.lms.deals.repositories;

import java.util.List;

import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.entities.Stages;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DealsRepository extends JpaRepository<Deals, Long> {

    public List<Deals> findByContextableTypeIgnoreCaseContainingAndContextableId(String contextableType,
            Long contextableId);

    public List<Deals> findByPipelines(Pipelines pipelines);

    public List<Deals> findByStages(Stages stages);

}
