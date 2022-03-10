package com.imocha.lms.deals.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.model.StatusesResponse;
import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.model.DealsResponse;
import com.imocha.lms.deals.model.UpdateDealsRequest;
import com.imocha.lms.deals.repositories.DealsRepository;
import com.imocha.lms.entities.Statuses;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.PeopleResponse;
import com.imocha.lms.leads.service.PeopleService;
import com.imocha.lms.lostReasons.model.LostReasonsResponse;
import com.imocha.lms.pipelines.entities.Pipelines;
import com.imocha.lms.pipelines.model.PipelinesResponse;
import com.imocha.lms.pipelines.service.PipelinesService;
import com.imocha.lms.stages.entities.Stages;
import com.imocha.lms.stages.model.StagesResponse;
import com.imocha.lms.stages.service.StagesService;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.service.UsersService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DealsService {

	@Autowired
	private DealsRepository dealsRepository;

	@Lazy
	@Autowired
	private PipelinesService pipelinesService;

	@Autowired
	private StagesService stagesService;

	@Autowired
	private UsersService usersService;

	@Lazy
	@Autowired
	private PeopleService peopleService;

	public Page<DealsResponse> page(PageableRequest pageableRequest) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
		Page<Deals> dealsPage = this.dealsRepository.findAll(pageRequest);

		List<DealsResponse> dealsResponseList = dealsPage.getContent().stream().map(deals -> {
			return this.mapDealsResponse(deals);
		}).collect(Collectors.toList());

		Page<DealsResponse> dealsResponsePageImpl = new PageImpl<>(dealsResponseList, pageRequest,
				dealsPage.getTotalElements());
		return dealsResponsePageImpl;
	}

	private DealsResponse mapDealsResponse(Deals deals) {
		DealsResponse dealsResponse = new DealsResponse();
		BeanUtils.copyProperties(deals, dealsResponse);

		StatusesResponse statusesResponse = new StatusesResponse();
		BeanUtils.copyProperties(deals.getStatuses(), statusesResponse);
		dealsResponse.setStatuses(statusesResponse);

		OwnerResponse ownerResponse = new OwnerResponse();
		BeanUtils.copyProperties(deals.getOwner(), ownerResponse);
		dealsResponse.setOwner(ownerResponse);

		if (deals.getLostReasons() != null) {
			LostReasonsResponse lostReasonsResponse = new LostReasonsResponse();
			BeanUtils.copyProperties(deals.getLostReasons(), lostReasonsResponse);
			dealsResponse.setLostReasons(lostReasonsResponse);
		}

		PipelinesResponse pipelinesResponse = new PipelinesResponse();
		BeanUtils.copyProperties(deals.getPipelines(), pipelinesResponse);
		dealsResponse.setPipelines(pipelinesResponse);

		StagesResponse stagesResponse = new StagesResponse();
		BeanUtils.copyProperties(deals.getStages(), stagesResponse);
		dealsResponse.setStages(stagesResponse);

		People people = peopleService.get(deals.getContextableId());
		PeopleResponse peopleResponse = new PeopleResponse();
		BeanUtils.copyProperties(people, peopleResponse);
		dealsResponse.setPerson(peopleResponse);

		return dealsResponse;
	}

	public Deals get(long id) {
		Optional<Deals> dOptional = dealsRepository.findById(id);
		if (!dOptional.isPresent()) {
			// TODO: throw error;
		}

		return dOptional.get();
	}

	public DealsResponse getDealsResponse(long id) {
		Deals deals = this.get(id);
		return this.mapDealsResponse(deals);
	}

	public long update(long id, UpdateDealsRequest request) {
		Deals deals = this.get(id);
		BeanUtils.copyProperties(request, deals);

		Pipelines pipelines = pipelinesService.get(request.getPipelinesId());
		deals.setPipelines(pipelines);

		Stages stages = stagesService.get(request.getStagesId());
		deals.setStages(stages);

		deals.setContextableId(request.getPersonId());

		Users owner = usersService.get(request.getOwnerId());
		deals.setOwner(owner);

		Deals savedDeals = dealsRepository.save(deals);
		return savedDeals.getId();
	}

	public long delete(long id) {
		this.get(id);
		dealsRepository.deleteById(id);
		return id;
	}

	public List<Statuses> getPersonDealsCountByStatus(Long id) {
		List<Deals> deals = this.dealsRepository.findByContextableTypeIgnoreCaseContainingAndContextableId("person",
				id);

		deals.stream().filter(deal -> deal.getStatuses().equals("status_open")).count();

		return null;
	}

	public long getTotalDealValueByPipelines(Pipelines pipelines) {
		List<Deals> dealsList = dealsRepository.findByPipelines(pipelines);
		long totalDealValue = 0;
		for (Deals deals : dealsList) {
			totalDealValue += deals.getValue();
		}
		return totalDealValue;
	}

	public int getDealsCountByPipelines(Pipelines pipelines) {
		List<Deals> dealsList = dealsRepository.findByPipelines(pipelines);
		return dealsList.size();
	}

	public Long getPersonOpenDealsCountByStatus(Long id) {
		List<Deals> deals = this.dealsRepository.findByContextableTypeIgnoreCaseContainingAndContextableId("person",
				id);

		return deals.stream().filter(deal -> deal.getStatuses().getName().equals("status_open")).count();
	}

	public Long getPersonClosedDealsCountByStatus(Long id) {
		List<Deals> deals = this.dealsRepository.findByContextableTypeIgnoreCaseContainingAndContextableId("person",
				id);

		return deals.stream().filter(deal -> deal.getStatuses().getName().equals("status_closed")).count();
	}

	public List<Deals> getDealsByPersonId(Long id) {
		List<Deals> deals = dealsRepository.findByContextableTypeIgnoreCaseContainingAndContextableId("person", id);

		return deals;
	}
}
