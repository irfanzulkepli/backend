package com.imocha.lms.activities.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.imocha.common.helper.UserHelper;
import com.imocha.common.model.PageableRequest;
import com.imocha.lms.activities.entities.Activities;
import com.imocha.lms.activities.entities.ActivityCollaborator;
import com.imocha.lms.activities.entities.ActivityParticipant;
import com.imocha.lms.activities.entities.ActivityTypes;
import com.imocha.lms.activities.model.ActivitiesRequest;
import com.imocha.lms.activities.model.ActivityListResponse;
import com.imocha.lms.activities.model.ActivityPageResponse;
import com.imocha.lms.activities.model.ActivityResponse;
import com.imocha.lms.activities.repositories.ActivitiesCollaboratorRepository;
import com.imocha.lms.activities.repositories.ActivitiesParticipantRepository;
import com.imocha.lms.activities.repositories.ActivitiesRepository;
import com.imocha.lms.activities.repositories.ActivityTypesRepository;
import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.common.service.StatusesService;
import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.model.DealsResponse;
import com.imocha.lms.deals.repositories.DealsRepository;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.ActivityTypeResponse;
import com.imocha.lms.leads.model.CollaboratorResponse;
import com.imocha.lms.leads.model.OrganizationResponse;
import com.imocha.lms.leads.model.OrganizationsResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.ParticipantResponse;
import com.imocha.lms.leads.model.PeopleResponse;
import com.imocha.lms.leads.repositories.PeopleRepository;
import com.imocha.lms.leads.service.OrganizationService;
import com.imocha.lms.leads.service.PeopleService;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.service.UsersService;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActivitiesService {

	@Autowired
	ActivitiesRepository activitiesRepository;

	@Autowired
	private PeopleRepository peopleRepository;

	@Autowired
	private DealsRepository dealsRepository;

	@Autowired
	ActivitiesCollaboratorRepository activitiesCollaboratorRepository;

	@Autowired
	ActivitiesParticipantRepository activitiesParticipantRepository;

	@Autowired
	ActivityTypesRepository activityTypesRepository;

	@Autowired
	UserHelper userHelper;

	@Autowired
	UsersService usersService;

	@Autowired
	StatusesService statusesService;

	@Lazy
	@Autowired
	PeopleService peopleService;

	@Lazy
	@Autowired
	private OrganizationService organizationService;

	public Page<ActivityPageResponse> page(PageableRequest pageableRequest) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
		Page<Activities> activitiesPage = activitiesRepository.findAll(pageRequest);

		List<ActivityPageResponse> activities = activitiesPage.getContent().stream().map(activity -> {
			return populateActivityPageResponse(activity);
		}).collect(Collectors.toList());

		Page<ActivityPageResponse> activityResponsePageImpl = new PageImpl<>(activities, pageRequest,
				activitiesPage.getTotalElements());
		return activityResponsePageImpl;
	}

	public List<ActivityListResponse> done() {
		List<ActivityListResponse> responseList = new ArrayList<ActivityListResponse>();

		Statuses status = getStatusByMarkAsDone(false);
		List<Activities> activities = activitiesRepository.findByStatuses(status);

		for (Activities activity : activities) {
			ActivityListResponse response = this.populateActivityListResponse(activity);
			responseList.add(response);
		}

		return responseList;
	}

	public ActivityResponse get(long id) {
		Optional<Activities> aOptional = this.activitiesRepository.findById(id);
		aOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
		return populateActivityResponse(aOptional.get());
	}

	public String getNextActivityByDeal(long dealId) {

		List<Activities> activityList = activitiesRepository.findByContextableTypeAndContextableId(
				ContextableTypes.DEAL, dealId);

		if (activityList.size() > 0) {

			Activities nearestActivity = activityList.get(0);
			Date nearestDate = activityList.get(0).getStartedAt();
			Date nearestTime = activityList.get(0).getStartTime();

			for (Activities activity : activityList) {

				if (activity.getStartedAt().compareTo(nearestDate) < 0) {
					nearestActivity = activity;
					nearestDate = activity.getStartedAt();
					nearestTime = activity.getStartTime();
				} else if (activity.getStartedAt().compareTo(nearestDate) == 0) {

					if (activity.getStartTime().compareTo(nearestTime) < 0) {
						nearestActivity = activity;
						nearestDate = activity.getStartedAt();
						nearestTime = activity.getStartTime();
					}
				}

			}

			String startedAt = this.getDateIgnoreTimezone(nearestActivity.getStartedAt());
			return startedAt + " (" + nearestActivity.getActivityTypes().getName() + ")";
		} else {
			return null;
		}
	}

	public List<Activities> getActivitiesByLeadId(Long id, ContextableTypes leadType) {
		List<Activities> activities = activitiesRepository.findByContextableTypeAndContextableId(leadType, id);

		return activities;
	}

	public List<People> getParticipantsByActivityId(Activities activity) {
		List<ActivityParticipant> activityParticipants = activitiesParticipantRepository.findByActivities(activity);

		List<People> peoples = activityParticipants.stream().map(participant -> {
			return participant.getPeople();
		}).collect(Collectors.toList());

		return peoples;
	}

	public List<ActivityListResponse> getActivitiesByContextableTypeAndContextableId(ContextableTypes contextableType,
			long contextableId) {

		List<Activities> activityList = activitiesRepository.findByContextableTypeAndContextableId(contextableType,
				contextableId);

		List<ActivityListResponse> responseList = new ArrayList<ActivityListResponse>();

		for (Activities activity : activityList) {
			ActivityListResponse response = this.populateActivityListResponse(activity);
			responseList.add(response);
		}
		return responseList;
	}

	public List<ActivityPageResponse> getLeadsActivitiesByContextableTypeAndContextableId(
			ContextableTypes contextableType, long contextableId) {

		List<Activities> activityList = activitiesRepository.findByContextableTypeAndContextableId(contextableType,
				contextableId);

		List<ActivityPageResponse> responseList = new ArrayList<ActivityPageResponse>();

		for (Activities activity : activityList) {
			ActivityPageResponse response = this.populateActivityPageResponse(activity);
			responseList.add(response);
		}
		return responseList;
	}

	public List<Users> getCollaboratorsByActivity(Activities activity) {
		List<ActivityCollaborator> activityCollaborators = activitiesCollaboratorRepository.findByActivities(activity);

		List<Users> collaborators = activityCollaborators.stream().map(collaborator -> {
			return collaborator.getUsers();
		}).collect(Collectors.toList());

		return collaborators;
	}

	public long addActiviy(ActivitiesRequest requestModel) {
		Activities activity = new Activities();
		BeanUtils.copyProperties(requestModel, activity);

		Statuses status = this.getStatusByMarkAsDone(requestModel.isMarkAsDone());
		activity.setStatuses(status);

		if (requestModel.getContextableType().equals(ContextableTypes.PERSON)) {
			activity.setContextableId(requestModel.getPersonsId());
		} else if (requestModel.getContextableType().equals(ContextableTypes.ORGANIZATION)) {
			activity.setContextableId(requestModel.getOrganizationsId());
		} else if (requestModel.getContextableType().equals(ContextableTypes.DEAL)) {
			activity.setContextableId(requestModel.getDealsId());
		}

		ActivityTypes activityType = activityTypesRepository.getById(requestModel.getActivityTypeId());
		activity.setActivityTypes(activityType);

		Activities newActivity = activitiesRepository.save(activity);

		for (long participantId : requestModel.getParticipantsIds()) {
			ActivityParticipant activityParticipant = this.getActivityParticipantByParticipantId(participantId,
					newActivity);
			activitiesParticipantRepository.save(activityParticipant);
		}

		for (long collaboratorId : requestModel.getCollaboratorsIds()) {
			ActivityCollaborator activityCollaborator = this.getActivityCollaboratorByCollaboratorId(collaboratorId,
					newActivity);
			activitiesCollaboratorRepository.save(activityCollaborator);
		}

		return newActivity.getId();
	}

	public ActivityResponse update(Long id, ActivitiesRequest requestModel) {
		Date dateNow = new Date();
		Optional<Activities> activityOptional = activitiesRepository.findById(id);
		activityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

		Activities activity = activityOptional.get();
		BeanUtils.copyProperties(requestModel, activity);

		List<ActivityParticipant> activityParticipants = activitiesParticipantRepository.findByActivities(activity);
		List<ActivityCollaborator> activityCollaborators = activitiesCollaboratorRepository.findByActivities(activity);

		activityParticipants.forEach(activityParticipant -> {
			activitiesParticipantRepository.delete(activityParticipant);
		});
		activityCollaborators.forEach(activityCollaborator -> {
			activitiesCollaboratorRepository.delete(activityCollaborator);
		});

		Statuses status = this.getStatusByMarkAsDone(requestModel.isMarkAsDone());
		activity.setStatuses(status);

		if (requestModel.getContextableType().equals(ContextableTypes.PERSON)) {
			activity.setContextableId(requestModel.getPersonsId());
		} else if (requestModel.getContextableType().equals(ContextableTypes.ORGANIZATION)) {
			activity.setContextableId(requestModel.getOrganizationsId());
		} else if (requestModel.getContextableType().equals(ContextableTypes.DEAL)) {
			activity.setContextableId(requestModel.getDealsId());
		}

		ActivityTypes activityType = activityTypesRepository.getById(requestModel.getActivityTypeId());
		activity.setActivityTypes(activityType);

		Activities newActivity = activitiesRepository.save(activity);

		for (long participantId : requestModel.getParticipantsIds()) {
			ActivityParticipant activityParticipant = this.getActivityParticipantByParticipantId(participantId,
					newActivity);
			activitiesParticipantRepository.save(activityParticipant);
		}

		for (long collaboratorId : requestModel.getCollaboratorsIds()) {
			ActivityCollaborator activityCollaborator = this.getActivityCollaboratorByCollaboratorId(collaboratorId,
					newActivity);
			activitiesCollaboratorRepository.save(activityCollaborator);
		}

		return populateActivityResponse(activity);
	}

	private Statuses getStatusByMarkAsDone(boolean markAsDone) {

		if (markAsDone) {
			return statusesService.findActivityDoneStatuses();
		} else {
			return statusesService.findActivityTodoStatuses();
		}
	}

	private ActivityParticipant getActivityParticipantByParticipantId(long participantId, Activities newActivity) {
		People people = peopleService.get(participantId);
		ActivityParticipant activityParticipant = new ActivityParticipant();
		activityParticipant.setPeople(people);
		activityParticipant.setActivities(newActivity);
		return activityParticipant;
	}

	public List<ActivityListResponse> list(ContextableTypes contextableType) {

		List<Activities> activityList = new ArrayList<Activities>();

		if (contextableType == null) {
			activityList = activitiesRepository.findAll();
		} else {
			activityList = activitiesRepository.findByContextableType(contextableType);
		}

		List<ActivityListResponse> responseList = new ArrayList<ActivityListResponse>();

		for (Activities activity : activityList) {
			ActivityListResponse response = this.populateActivityListResponse(activity);
			responseList.add(response);
		}
		return responseList;
	}

	public List<ActivityListResponse> getActivitiesByContextableType(ContextableTypes contextableType) {

		List<Activities> activityList = activitiesRepository.findByContextableType(contextableType);

		List<ActivityListResponse> responseList = new ArrayList<ActivityListResponse>();

		for (Activities activity : activityList) {
			ActivityListResponse response = this.populateActivityListResponse(activity);
			responseList.add(response);
		}
		return responseList;
	}

	private ActivityCollaborator getActivityCollaboratorByCollaboratorId(long collaboratorId, Activities newActivity) {
		Users collaborator = usersService.get(collaboratorId);
		ActivityCollaborator activityCollaborator = new ActivityCollaborator();
		activityCollaborator.setUsers(collaborator);
		activityCollaborator.setActivities(newActivity);
		return activityCollaborator;
	}

	public Long deleteById(Long id) {
		Optional<Activities> activityOptional = activitiesRepository.findById(id);
		activityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

		Activities activity = activityOptional.get();
		List<ActivityParticipant> activityParticipants = activitiesParticipantRepository.findByActivities(activity);
		List<ActivityCollaborator> activityCollaborators = activitiesCollaboratorRepository.findByActivities(activity);

		activityParticipants.forEach(activityParticipant -> {
			activitiesParticipantRepository.delete(activityParticipant);
		});
		activityCollaborators.forEach(activityCollaborator -> {
			activitiesCollaboratorRepository.delete(activityCollaborator);
		});

		activitiesRepository.deleteById(id);

		return id;
	}

	public ActivityResponse markAsDone(Long id) {
		Date dateNow = new Date();
		Optional<Activities> activityOptional = activitiesRepository.findById(id);

		Long doneStatusId = (long) 12;
		activityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

		Activities activity = activityOptional.get();

		Statuses status = statusesService.findById(doneStatusId);
		activity.setStatuses(status);

		activitiesRepository.save(activity);

		return populateActivityResponse(activity);
	}

	public List<ActivityTypeResponse> listActivityTypes() {
		List<ActivityTypes> activityTypes = activityTypesRepository.findAll();

		List<ActivityTypeResponse> activityTypeResponses = activityTypes.stream().map(activityType -> {
			ActivityTypeResponse activityTypeResponse = new ActivityTypeResponse();
			BeanUtils.copyProperties(activityType, activityTypeResponse);

			return activityTypeResponse;
		}).collect(Collectors.toList());

		return activityTypeResponses;
	}

	private ActivityListResponse populateActivityListResponse(Activities activity) {

		ActivityListResponse activityResponse = new ActivityListResponse();
		List<People> pariticipants = getParticipantsByActivityId(activity);
		List<Users> collaborators = getCollaboratorsByActivity(activity);

		List<ParticipantResponse> participantsRes = pariticipants.stream().map(participant -> {
			ParticipantResponse participantResponse = new ParticipantResponse();
			BeanUtils.copyProperties(participant, participantResponse);

			return participantResponse;
		}).collect(Collectors.toList());

		List<CollaboratorResponse> collaboratorsRes = collaborators.stream().map(collaborator -> {
			CollaboratorResponse collaboratorResponse = new CollaboratorResponse();
			BeanUtils.copyProperties(collaborator, collaboratorResponse);

			return collaboratorResponse;
		}).collect(Collectors.toList());

		String startedAt = this.getDateIgnoreTimezone(activity.getStartedAt());
		String endedAt = this.getDateIgnoreTimezone(activity.getEndedAt());
		String startTime = this.getTimeIgnoreTimezone(activity.getStartTime());
		String endTime = this.getTimeIgnoreTimezone(activity.getEndTime());

		activityResponse.setStartedAt(startedAt);
		activityResponse.setEndedAt(endedAt);
		activityResponse.setStartTime(startTime);
		activityResponse.setEndTime(endTime);

		activityResponse.setParticipants(participantsRes);
		activityResponse.setCollaborators(collaboratorsRes);

		activityResponse.setStatus(activity.getStatuses());

		ActivityTypeResponse activityTypeResponse = new ActivityTypeResponse();
		BeanUtils.copyProperties(activity.getActivityTypes(), activityTypeResponse);
		activityResponse.setActivityType(activityTypeResponse);

		OwnerResponse createdByResponse = new OwnerResponse();
		Users createdBy = usersService.getByKeycloakId(activity.getCreatedBy());
		BeanUtils.copyProperties(createdBy, createdByResponse);
		activityResponse.setCreatedBy(createdByResponse);

		BeanUtils.copyProperties(activity, activityResponse);

		return activityResponse;
	}

	private ActivityPageResponse populateActivityPageResponse(Activities activity) {

		ActivityPageResponse activityResponse = new ActivityPageResponse();
		List<People> pariticipants = getParticipantsByActivityId(activity);
		List<Users> collaborators = getCollaboratorsByActivity(activity);

		List<ParticipantResponse> participantsRes = pariticipants.stream().map(participant -> {
			ParticipantResponse participantResponse = new ParticipantResponse();
			BeanUtils.copyProperties(participant, participantResponse);

			return participantResponse;
		}).collect(Collectors.toList());

		List<CollaboratorResponse> collaboratorsRes = collaborators.stream().map(collaborator -> {
			CollaboratorResponse collaboratorResponse = new CollaboratorResponse();
			BeanUtils.copyProperties(collaborator, collaboratorResponse);

			return collaboratorResponse;
		}).collect(Collectors.toList());

		if (activity.getContextableType().equals(ContextableTypes.PERSON)) {
			People people = peopleRepository.getById(activity.getContextableId());
			PeopleResponse peopleResponse = new PeopleResponse();
			BeanUtils.copyProperties(people, peopleResponse);

			activityResponse.setPerson(peopleResponse);
		} else if (activity.getContextableType().equals(ContextableTypes.ORGANIZATION)) {
			OrganizationsResponse organization = organizationService.getById(activity.getContextableId());
			OrganizationResponse organizationResponse = new OrganizationResponse();
			BeanUtils.copyProperties(organization, organizationResponse);

			activityResponse.setOrganization(organizationResponse);
		} else if (activity.getContextableType().equals(ContextableTypes.DEAL)) {
			Deals deal = dealsRepository.getById(activity.getContextableId());
			DealsResponse dealsResponse = new DealsResponse();
			BeanUtils.copyProperties(deal, dealsResponse);

			activityResponse.setDeal(dealsResponse);
		}

		ActivityTypeResponse activityTypeResponse = new ActivityTypeResponse();
		ActivityTypes activityTypes = activity.getActivityTypes();
		BeanUtils.copyProperties(activityTypes, activityTypeResponse);

		activityResponse.setActivityType(activityTypeResponse);
		activityResponse.setCollaborators(collaboratorsRes);
		activityResponse.setParticipants(participantsRes);
		activityResponse.setStartedAt(activity.getStartedAt());
		activityResponse.setEndedAt(activity.getEndedAt());
		activityResponse.setStatus(activity.getStatuses());

		OwnerResponse createdByResponse = new OwnerResponse();
		Users createdBy = usersService.getByKeycloakId(activity.getCreatedBy());
		BeanUtils.copyProperties(createdBy, createdByResponse);
		activityResponse.setCreatedBy(createdByResponse);

		BeanUtils.copyProperties(activity, activityResponse);

		return activityResponse;
	}

	private ActivityResponse populateActivityResponse(Activities activity) {

		ActivityResponse activityResponse = new ActivityResponse();
		List<People> pariticipants = getParticipantsByActivityId(activity);
		List<Users> collaborators = getCollaboratorsByActivity(activity);

		List<ParticipantResponse> participantsRes = pariticipants.stream().map(participant -> {
			ParticipantResponse participantResponse = new ParticipantResponse();
			BeanUtils.copyProperties(participant, participantResponse);

			return participantResponse;
		}).collect(Collectors.toList());

		List<CollaboratorResponse> collaboratorsRes = collaborators.stream().map(collaborator -> {
			CollaboratorResponse collaboratorResponse = new CollaboratorResponse();
			BeanUtils.copyProperties(collaborator, collaboratorResponse);

			return collaboratorResponse;
		}).collect(Collectors.toList());

		if (activity.getContextableType().equals(ContextableTypes.PERSON)) {
			People people = peopleRepository.getById(activity.getContextableId());
			PeopleResponse peopleResponse = new PeopleResponse();
			BeanUtils.copyProperties(people, peopleResponse);

			activityResponse.setPerson(peopleResponse);
		} else if (activity.getContextableType().equals(ContextableTypes.ORGANIZATION)) {
			OrganizationsResponse organization = organizationService.getById(activity.getContextableId());
			OrganizationResponse organizationResponse = new OrganizationResponse();
			BeanUtils.copyProperties(organization, organizationResponse);

			activityResponse.setOrganization(organizationResponse);
		} else if (activity.getContextableType().equals(ContextableTypes.DEAL)) {

		}

		ActivityTypeResponse activityTypeResponse = new ActivityTypeResponse();
		ActivityTypes activityTypes = activity.getActivityTypes();
		BeanUtils.copyProperties(activityTypes, activityTypeResponse);

		activityResponse.setActivityType(activityTypeResponse);
		activityResponse.setCollaborators(collaboratorsRes);
		activityResponse.setParticipants(participantsRes);
		activityResponse.setStatus(activity.getStatuses());

		OwnerResponse createdByResponse = new OwnerResponse();
		Users createdBy = usersService.getByKeycloakId(activity.getCreatedBy());
		BeanUtils.copyProperties(createdBy, createdByResponse);
		activityResponse.setCreatedBy(createdByResponse);

		String startedAt = this.getDateIgnoreTimezone(activity.getStartedAt());
		String endedAt = this.getDateIgnoreTimezone(activity.getEndedAt());
		String startTime = this.getTimeIgnoreTimezone(activity.getStartTime());
		String endTime = this.getTimeIgnoreTimezone(activity.getEndTime());

		activityResponse.setStartedAt(startedAt);
		activityResponse.setEndedAt(endedAt);
		activityResponse.setStartTime(startTime);
		activityResponse.setEndTime(endTime);

		BeanUtils.copyProperties(activity, activityResponse);

		return activityResponse;
	}

	private String getDateIgnoreTimezone(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		return String.format("%04d", year) + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
	}

	private String getTimeIgnoreTimezone(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		return String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second);
	}
}
