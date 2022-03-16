package com.imocha.lms.deals.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.common.entities.Taggables;
import com.imocha.lms.common.entities.Tags;
import com.imocha.lms.common.model.StatusesResponse;
import com.imocha.lms.common.model.TagResponse;
import com.imocha.lms.common.service.StatusesService;
import com.imocha.lms.common.service.TagService;
import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.entities.LostReasons;
import com.imocha.lms.deals.model.AddDealsRequest;
import com.imocha.lms.deals.model.DealsListResponse;
import com.imocha.lms.deals.model.DealsPageResponse;
import com.imocha.lms.deals.model.DealsResponse;
import com.imocha.lms.deals.model.LostReasonsResponse;
import com.imocha.lms.deals.model.UpdateDealsRequest;
import com.imocha.lms.deals.model.UpdateDealsTagRequest;
import com.imocha.lms.deals.model.UpdateDealsToLostRequest;
import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.entities.Stages;
import com.imocha.lms.deals.pipelines.model.PipelinesResponse;
import com.imocha.lms.deals.pipelines.model.StagesResponse;
import com.imocha.lms.deals.pipelines.service.PipelinesService;
import com.imocha.lms.deals.pipelines.service.StagesService;
import com.imocha.lms.deals.repositories.DealsRepository;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.PeopleResponse;
import com.imocha.lms.leads.service.PeopleService;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.service.UsersService;

import org.apache.commons.lang3.StringUtils;
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

	@Lazy
	@Autowired
	private StagesService stagesService;

	@Autowired
	private UsersService usersService;

	@Lazy
	@Autowired
	private PeopleService peopleService;

	@Autowired
	private StatusesService statusesService;

	@Autowired
	private LostReasonsService lostReasonsService;

	@Autowired
	private TagService tagService;

	public Page<DealsPageResponse> page(PageableRequest pageableRequest) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
		Page<Deals> dealsPage = this.dealsRepository.findAll(pageRequest);

		List<DealsPageResponse> dealsResponseList = dealsPage.getContent().stream().map(deals -> {
			return this.mapDealsToDealsPageResponse(deals);
		}).collect(Collectors.toList());

		Page<DealsPageResponse> dealsResponsePageImpl = new PageImpl<>(dealsResponseList, pageRequest,
				dealsPage.getTotalElements());
		return dealsResponsePageImpl;
	}

	private DealsPageResponse mapDealsToDealsPageResponse(Deals deals) {
		DealsPageResponse pageResponse = new DealsPageResponse();
		BeanUtils.copyProperties(deals, pageResponse);

		StatusesResponse statusesResponse = new StatusesResponse();
		BeanUtils.copyProperties(deals.getStatuses(), statusesResponse);
		pageResponse.setStatuses(statusesResponse);

		OwnerResponse ownerResponse = new OwnerResponse();
		BeanUtils.copyProperties(deals.getOwner(), ownerResponse);
		pageResponse.setOwner(ownerResponse);

		List<TagResponse> tags = tagService.getLeadsTagById(deals.getId(), "deals");
		pageResponse.setTags(tags);

		return pageResponse;
	}

	public List<DealsListResponse> listPipelineView(String id) {
		long pipelinesId = 0;
		if (StringUtils.isBlank(id)) {
			pipelinesId = pipelinesService.getFirstPipelines().getId();
		} else {
			pipelinesId = Integer.parseInt(id);
		}

		Pipelines pipelines = pipelinesService.get(pipelinesId);
		List<Deals> dealsList = dealsRepository.findByPipelines(pipelines);
		List<DealsListResponse> responseList = new ArrayList<DealsListResponse>();

		for (Deals deals : dealsList) {
			DealsListResponse response = this.mapDealsToDealsListResponse(deals);
			responseList.add(response);
		}

		return responseList;
	}

	private DealsListResponse mapDealsToDealsListResponse(Deals deals) {
		DealsListResponse dealsResponse = new DealsListResponse();
		BeanUtils.copyProperties(deals, dealsResponse);

		StatusesResponse statusesResponse = new StatusesResponse();
		BeanUtils.copyProperties(deals.getStatuses(), statusesResponse);
		dealsResponse.setStatuses(statusesResponse);

		OwnerResponse ownerResponse = new OwnerResponse();
		BeanUtils.copyProperties(deals.getOwner(), ownerResponse);
		dealsResponse.setOwner(ownerResponse);

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

		List<TagResponse> tags = tagService.getLeadsTagById(deals.getId(), "deals");
		dealsResponse.setTags(tags);

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
		return this.mapDealsToDealsResponse(deals);
	}

	private DealsResponse mapDealsToDealsResponse(Deals deals) {
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

		List<TagResponse> tags = tagService.getLeadsTagById(deals.getId(), "deals");
		dealsResponse.setTags(tags);

		return dealsResponse;
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

	public long updateStage(long id,long stagesId) {
		Deals deals = this.get(id);
		Stages stages = stagesService.get(stagesId);
		deals.setStages(stages);

		Deals savedDeals = dealsRepository.save(deals);
		return savedDeals.getId();
	}

	public long updateValue(long id,long dealValue) {
		Deals deals = this.get(id);
		deals.setValue(dealValue);

		Deals savedDeals = dealsRepository.save(deals);
		return savedDeals.getId();
	}

	public long updatePersonId(long id,long personId) {
		Deals deals = this.get(id);
		deals.setContextableId(personId);

		Deals savedDeals = dealsRepository.save(deals);
		return savedDeals.getId();
	}

	public long updateExpiredDate(long id,Date expiredDate) {
		Deals deals = this.get(id);
		deals.setExpiredAt(expiredDate);

		Deals savedDeals = dealsRepository.save(deals);
		return savedDeals.getId();
	}

	public long updateDescription(long id,String description) {
		Deals deals = this.get(id);
		deals.setDescription(description);

		Deals savedDeals = dealsRepository.save(deals);
		return savedDeals.getId();
	}

	public long updateDealsToLost(long id, UpdateDealsToLostRequest request) {
		Deals deals = this.get(id);

		Statuses statuses = statusesService.findDealLostStatuses();
		deals.setStatuses(statuses);

		LostReasons lostReasons = lostReasonsService.get(request.getLostReasonsId());
		deals.setLostReasons(lostReasons);

		deals.setComment(request.getComment());

		Deals savedDeals = dealsRepository.save(deals);
		return savedDeals.getId();
	}

	public long updateDealsToWon(long id) {
		Deals deals = this.get(id);

		Statuses statuses = statusesService.findDealWonStatuses();
		deals.setStatuses(statuses);

		Deals savedDeals = dealsRepository.save(deals);
		return savedDeals.getId();
	}

	public long updateDealsTag(long id, UpdateDealsTagRequest request) {
		Deals deals = this.get(id);

		List<Taggables> taggablesList = tagService.getTaggableByTaggableIdAndTaggableType(deals.getId(), "deals");
		for (Taggables taggables : taggablesList) {
			tagService.deleteByEntity(taggables);
		}

		for (long tagId : request.getTagIds()) {
			Tags tag = tagService.getByTagId(tagId);

			Taggables taggable = new Taggables();
			taggable.setTaggableId(deals.getId());
			taggable.setTaggableType("deals");
			taggable.setTags(tag);

			tagService.save(taggable);
		}

		Deals savedDeals = dealsRepository.save(deals);
		return savedDeals.getId();
	}

	public int checkDealsExistByStages(Stages stages) {
		List<Deals> dealsList = dealsRepository.findByStages(stages);
		return dealsList.size();
	}

	public long updateAllDealsToNewStage(long oldStageId, long newStageId) {
		Stages oldStages = stagesService.get(oldStageId);
		Stages newStages = stagesService.get(newStageId);

		List<Deals> dealsList = dealsRepository.findByStages(oldStages);
		for (Deals deals : dealsList) {
			deals.setStages(newStages);
		}
		dealsRepository.saveAll(dealsList);

		return newStages.getId();
	}

	public long delete(long id) {
		this.get(id);
		dealsRepository.deleteById(id);
		return id;
	}

	public List<Statuses> getLeadsDealsCountByStatus(Long id) {
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

	public Long getLeadsOpenDealsCountByStatus(Long id, String leadType) {
		List<Deals> deals = this.dealsRepository.findByContextableTypeIgnoreCaseContainingAndContextableId(leadType,
				id);

		return deals.stream().filter(deal -> deal.getStatuses().getName().equals("status_open")).count();
	}

	public Long getLeadsClosedDealsCountByStatus(Long id, String leadType) {
		List<Deals> deals = this.dealsRepository.findByContextableTypeIgnoreCaseContainingAndContextableId(leadType,
				id);

		return deals.stream().filter(deal -> deal.getStatuses().getName().equals("status_closed")).count();
	}

	public List<Deals> getDealsByLeadId(Long id, String leadType) {
		List<Deals> deals = dealsRepository.findByContextableTypeIgnoreCaseContainingAndContextableId(leadType, id);

		return deals;
	}

	public long addDeals(AddDealsRequest request) {
		Users user = usersService.get(1);
		Deals deals = new Deals();

		deals.setContextableType("App\\Models\\CRM\\Person\\Person");
		Statuses statuses = statusesService.findDealOpenStatuses();
		deals.setStatuses(statuses);

		deals.setCreatedBy(user);
		deals.setTitle(request.getTitle());
		deals.setDescription(request.getDescription());
		deals.setExpiredAt(request.getExpiredAt());
		deals.setValue(request.getValue());
		
		Pipelines pipelines = pipelinesService.get(request.getPipelinesId());
		deals.setPipelines(pipelines);

		Stages stages = stagesService.get(request.getStagesId());
		deals.setStages(stages);

		deals.setContextableId(request.getPersonId());

		Users owner = usersService.get(request.getOwnerId());
		deals.setOwner(owner);
		log.info(deals.toString());
		Deals savedDeals = dealsRepository.save(deals);
		return savedDeals.getId();
	}
}
