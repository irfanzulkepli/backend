package com.imocha.lms.activities.service;

import java.util.ArrayList;
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
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.ActivityTypeResponse;
import com.imocha.lms.leads.model.CollaboratorResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.ParticipantResponse;
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
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActivitiesService {

	@Autowired
	ActivitiesRepository activitiesRepository;

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
	OrganizationService organizationService;

	@Autowired
	StatusesService statusesService;

	@Lazy
	@Autowired
	PeopleService peopleService;

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

	public ActivityResponse get(long id) {
		Optional<Activities> aOptional = this.activitiesRepository.findById(id);
		aOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
		return populateActivityResponse(aOptional.get());
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

	public List<People> getParticipantsByActivityId(Activities activity) {
		List<ActivityParticipant> activityParticipants = activitiesParticipantRepository.findByActivities(activity);

		List<People> peoples = activityParticipants.stream().map(participant -> {
			return participant.getPeople();
		}).collect(Collectors.toList());

		return peoples;
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

		Users user = userHelper.getCurrentLoginUser();
		activity.setUsers(user);

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

		activity.setUpdatedAt(dateNow);

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

	private boolean getMarkAsDoneByStatus(Statuses status) {

		Statuses doneStatus = statusesService.findActivityDoneStatuses();

		if (status.getId() == doneStatus.getId()) {
			return true;
		} else {
			return false;
		}
	}

	private ActivityParticipant getActivityParticipantByParticipantId(long participantId, Activities newActivity) {
		People people = peopleService.get(participantId);
		ActivityParticipant activityParticipant = new ActivityParticipant();
		activityParticipant.setPeople(people);
		activityParticipant.setActivities(newActivity);
		return activityParticipant;
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

		if (activityOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

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
		if (activityOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Activities activity = activityOptional.get();

		Statuses status = statusesService.findById(doneStatusId);
		activity.setStatuses(status);
		activity.setUpdatedAt(dateNow);

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

		ActivityListResponse response = new ActivityListResponse();
		// List<People> pariticipants = this.getParticipantsByActivityId(activity);
		// List<Users> collaborators = this.getCollaboratorsByActivity(activity);

		// List<ParticipantResponse> participantsRes =
		// pariticipants.stream().map(participant -> {
		// ParticipantResponse participantResponse = new ParticipantResponse();
		// BeanUtils.copyProperties(participant, participantResponse);

		// return participantResponse;
		// }).collect(Collectors.toList());

		// List<CollaboratorResponse> collaboratorsRes =
		// collaborators.stream().map(collaborator -> {
		// CollaboratorResponse collaboratorResponse = new CollaboratorResponse();
		// BeanUtils.copyProperties(collaborator, collaboratorResponse);

		// return collaboratorResponse;
		// }).collect(Collectors.toList());

		// ActivityTypeResponse activityTypeResponse = new ActivityTypeResponse();
		// ActivityTypes activityTypes = activity.getActivityTypes();
		// BeanUtils.copyProperties(activityTypes, activityTypeResponse);

		// response.setActivityType(activityTypeResponse);
		// response.setCollaborators(collaboratorsRes);
		// response.setParticipants(participantsRes);
		// response.setId(activity.getId());
		// response.setTitle(activity.getTitle());
		// response.setDescription(activity.getDescription());
		// response.setStatus(activity.getStatuses());

		BeanUtils.copyProperties(activity, response);

		// OwnerResponse owner = new OwnerResponse();
		// BeanUtils.copyProperties(activity.getUsers(), owner);
		// response.setCreatedBy(owner);

		return response;
	}

	private ActivityPageResponse populateActivityPageResponse(Activities activity) {

		ActivityPageResponse activityResponse = new ActivityPageResponse();
		// List<People> pariticipants = getParticipantsByActivityId(activity);
		// List<Users> collaborators = getCollaboratorsByActivity(activity);

		// List<ParticipantResponse> participantsRes =
		// pariticipants.stream().map(participant -> {
		// ParticipantResponse participantResponse = new ParticipantResponse();
		// BeanUtils.copyProperties(participant, participantResponse);

		// return participantResponse;
		// }).collect(Collectors.toList());

		// List<CollaboratorResponse> collaboratorsRes =
		// collaborators.stream().map(collaborator -> {
		// CollaboratorResponse collaboratorResponse = new CollaboratorResponse();
		// BeanUtils.copyProperties(collaborator, collaboratorResponse);

		// return collaboratorResponse;
		// }).collect(Collectors.toList());

		// ActivityTypeResponse activityTypeResponse = new ActivityTypeResponse();
		// ActivityTypes activityTypes = activity.getActivityTypes();
		// BeanUtils.copyProperties(activityTypes, activityTypeResponse);

		// activityResponse.setActivityType(activityTypeResponse);
		// activityResponse.setCollaborators(collaboratorsRes);
		// activityResponse.setParticipants(participantsRes);
		// activityResponse.setStatus(activity.getStatuses());

		BeanUtils.copyProperties(activity, activityResponse);

		// OwnerResponse ownerResponse = new OwnerResponse();
		// BeanUtils.copyProperties(activity.getUsers(), ownerResponse);
		// activityResponse.setCreatedBy(ownerResponse);

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

		ActivityTypeResponse activityTypeResponse = new ActivityTypeResponse();
		ActivityTypes activityTypes = activity.getActivityTypes();
		BeanUtils.copyProperties(activityTypes, activityTypeResponse);

		activityResponse.setActivityType(activityTypeResponse);
		activityResponse.setCollaborators(collaboratorsRes);
		activityResponse.setParticipants(participantsRes);

		log.info(activity.getStartedAt().toString());
		log.info(activity.getStartTime().toString());
		log.info(activity.getEndedAt().toString());
		log.info(activity.getEndTime().toString());

		activityResponse.setStatus(activity.getStatuses());
		activityResponse.setMarkAsDone(this.getMarkAsDoneByStatus(activity.getStatuses()));

		BeanUtils.copyProperties(activity, activityResponse);

		log.info(activity.getStartedAt().toString());
		log.info(activity.getStartTime().toString());
		log.info(activity.getEndedAt().toString());
		log.info(activity.getEndTime().toString());

		OwnerResponse ownerResponse = new OwnerResponse();
		BeanUtils.copyProperties(activity.getUsers(), ownerResponse);
		activityResponse.setCreatedBy(ownerResponse);

		return activityResponse;
	}
}
