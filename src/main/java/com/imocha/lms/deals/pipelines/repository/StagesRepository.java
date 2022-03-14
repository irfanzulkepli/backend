package com.imocha.lms.deals.pipelines.repository;

import java.util.List;

import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.entities.Stages;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StagesRepository extends JpaRepository<Stages, Long> {
    public List<Stages> findByPipelines(Pipelines pipelines);
}
