package com.imocha.lms.deals.repositories;

import java.util.List;

import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.entities.Stages;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealsRepository extends JpaRepository<Deals, Long> {

	public List<Deals> findByContextableTypeAndContextableId(ContextableTypes contextableType, Long contextableId);

	public Page<Deals> findByContextableTypeAndContextableId(Pageable page, ContextableTypes contextableType,
			Long contextableId);

	public List<Deals> findByPipelines(Pipelines pipelines);

	public List<Deals> findByStages(Stages stages);

	public Page<Deals> findAll(Specification<Deals> spec, Pageable pageable);

}
