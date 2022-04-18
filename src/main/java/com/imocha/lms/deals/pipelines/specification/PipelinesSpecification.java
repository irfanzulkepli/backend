package com.imocha.lms.deals.pipelines.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.entities.Pipelines_;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PipelinesSpecification {

    public Specification<Pipelines> getSpecification(PageableRequest request) {

        Specification<Pipelines> spec = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            Predicate activePredicate = criteriaBuilder.equal(root.get(Pipelines_.ACTIVE), true);
            predicates.add(activePredicate);
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return spec;
    }
}
