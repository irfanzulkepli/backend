package com.imocha.lms.deals.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.model.DealPageRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DealSpecification {

    public Specification<Deals> getDealSpecification(DealPageRequest request) {

        Specification<Deals> spec = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(request.getSearch())) {
                Predicate titlePredicate = criteriaBuilder.like(root.get("title"), request.getSearch() + "%");
                predicates.add(titlePredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return spec;
    }
}
