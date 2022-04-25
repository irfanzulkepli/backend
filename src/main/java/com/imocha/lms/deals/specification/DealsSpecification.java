package com.imocha.lms.deals.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import com.imocha.common.helper.DateHelper;
import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.common.entities.Statuses_;
import com.imocha.lms.common.service.TagService;
import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.entities.Deals_;
import com.imocha.lms.deals.model.DealPageRequest;
import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.entities.Pipelines_;
import com.imocha.lms.deals.service.ProposalsService;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.entities.Users_;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DealsSpecification {

	@Autowired
	private ProposalsService proposalsService;

	@Autowired
	private TagService tagService;

	@Autowired
	private DateHelper dateHelper;

	public Specification<Deals> getDealSpecification(DealPageRequest request) {

		Specification<Deals> spec = (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (StringUtils.isNotBlank(request.getSearch())) {

				List<Predicate> searchPredicates = new ArrayList<>();

				Predicate titlePredicate = criteriaBuilder.like(root.get(Deals_.TITLE),
						"%" + request.getSearch() + "%");
				searchPredicates.add(titlePredicate);

				Predicate valuePredicate = criteriaBuilder.like(root.get(Deals_.VALUE).as(String.class),
						"%" + request.getSearch() + "%");
				searchPredicates.add(valuePredicate);

				Join<Deals, Users> joinOwner = root.join(Deals_.OWNER);
				Predicate ownerPredicate1 = criteriaBuilder.like(joinOwner.get(Users_.FIRST_NAME),
						"%" + request.getSearch() + "%");
				searchPredicates.add(ownerPredicate1);
				Predicate ownerPredicate2 = criteriaBuilder.like(joinOwner.get(Users_.LAST_NAME),
						"%" + request.getSearch() + "%");
				searchPredicates.add(ownerPredicate2);

				Join<Deals, Statuses> joinStatus = root.join(Deals_.STATUSES);
				Predicate statusPredicate = criteriaBuilder.like(joinStatus.get(Statuses_.NAME),
						"%" + request.getSearch() + "%");
				searchPredicates.add(statusPredicate);

				Predicate combinedPredicate = criteriaBuilder.or(searchPredicates.toArray(new Predicate[0]));
				predicates.add(combinedPredicate);
			}

			Join<Deals, Pipelines> joinPipelines = root.join(Deals_.PIPELINES);
			if (StringUtils.isNotBlank(request.getPipelineId())) {
				Predicate pipelinePredicate = criteriaBuilder.equal(joinPipelines.get(Pipelines_.ID),
						request.getPipelineId());
				predicates.add(pipelinePredicate);
			} else {
				Predicate pipelinePredicate = criteriaBuilder.equal(joinPipelines.get(Pipelines_.ACTIVE), true);
				predicates.add(pipelinePredicate);
			}

			if (request.getTagIds().size() > 0) {

				Set<Long> dealIds = tagService.getTaggableDealIdsByTagIdssAndTaggableType(request.getTagIds());

				Expression<Deals> dealsExpression = root.get(Deals_.ID);
				Predicate proposalPredicate = dealsExpression.in(dealIds);
				predicates.add(proposalPredicate);

			}

			if (request.getHasProposals() == 1) {

				Set<Long> dealIds = proposalsService.getDealIdsFromProposalWithDeals();

				Expression<Deals> dealsExpression = root.get(Deals_.ID);
				Predicate proposalPredicate = dealsExpression.in(dealIds);
				predicates.add(proposalPredicate);

			} else if (request.getHasProposals() == 2) {

				Set<Long> dealIds = proposalsService.getDealIdsFromProposalWithoutDeals();

				Expression<Deals> dealsExpression = root.get(Deals_.ID);
				Predicate proposalPredicate = dealsExpression.in(dealIds);
				predicates.add(proposalPredicate);

			}

			if (StringUtils.isNotBlank(request.getDateFrom()) && StringUtils.isNotBlank(request.getDateTo())) {
				Date dateFrom = dateHelper.getDateFromString(request.getDateFrom());
				Date dateTo = dateHelper.getDateFromString(request.getDateTo());

				Predicate datePredicate = criteriaBuilder.between(root.get(Deals_.CREATED_AT), dateFrom, dateTo);
				predicates.add(datePredicate);

			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};

		return spec;
	}
}
