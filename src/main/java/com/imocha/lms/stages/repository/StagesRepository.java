package com.imocha.lms.stages.repository;

import java.util.List;

import com.imocha.lms.pipelines.entities.Pipelines;
import com.imocha.lms.stages.entities.Stages;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StagesRepository extends JpaRepository<Stages, Long> {
    public List<Stages> findByPipelines(Pipelines pipelines);
}
