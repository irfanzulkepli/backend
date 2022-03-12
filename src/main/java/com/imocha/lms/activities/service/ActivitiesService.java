package com.imocha.lms.activities.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.activities.entities.Activities;
import com.imocha.lms.activities.entities.ActivityCollaborator;
import com.imocha.lms.activities.entities.ActivityParticipant;
import com.imocha.lms.activities.entities.ActivityTypes;
import com.imocha.lms.activities.model.ActivitiesRequest;
import com.imocha.lms.activities.repositories.ActivitiesCollaboratorRepository;
import com.imocha.lms.activities.repositories.ActivitiesParticipantRepository;
import com.imocha.lms.activities.repositories.ActivitiesRepository;
import com.imocha.lms.activities.repositories.ActivityTypesRepository;
import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.common.model.ActivityResponse;
import com.imocha.lms.common.service.StatusesService;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.ActivityTypeResponse;
import com.imocha.lms.leads.model.CollaboratorResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.ParticipantResponse;
import com.imocha.lms.leads.repositories.PeopleRepository;
import com.imocha.lms.leads.service.PeopleService;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.service.UsersService;

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

	@Lazy
	@Autowired
	PeopleRepository peopleRepository;

	@Autowired
	UsersService usersService;

	@Autowired
	StatusesService statusesService;

	@Lazy
	@Autowired
	PeopleService peopleService;

	public Page<ActivityResponse> page(PageableRequest pageableRequest) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
		Page<Activities> activitiesPage = activitiesRepository.findAll(pageRequest);

		List<ActivityResponse> activities = activitiesPage.getContent().stream().map(activity -> {
			return populateActivityResponse(activity);
		}).collect(Collectors.toList());

		Page<ActivityResponse> activityResponsePageImpl = new PageImpl<>(activities, pageRequest,
				activitiesPage.getTotalElements());
		return activityResponsePageImpl;
	}

	public List<Activities> getActivitiesByLeadId(Long id, String leadType) {
		List<Activities> activities = activitiesRepository
				.findByContextableTypeIgnoreCaseContainingAndContextableId(leadType, id);

		return activities;
	}

	public List<People> getParticipantsByActivityId(Activities activity) {
		List<People> peoples = activity.getActivityParticipant().stream().map(participant -> {
			return participant.getPeople();
		}).collect(Collectors.toList());

		return peoples;
	}

	public List<Users> getCollaboratorsByActivity(Activities activiy) {
		List<Users> collaborators = activiy.getActivityCollaborator().stream().map(collaborator -> {
			return collaborator.getUsers();
		}).collect(Collectors.toList());

		return collaborators;
	}

	public ActivityResponse addActiviy(ActivitiesRequest requestModel) {
		Date dateNow = new Date();
		Activities activity = new Activities();
		BeanUtils.copyProperties(requestModel, activity);

		Users user = usersService.get(requestModel.getCreatedById());
		activity.setUsers(user);

		Statuses status = statusesService.findById(requestModel.getStatusId());
		activity.setStatuses(status);

		ActivityTypes activityType = activityTypesRepository.getById(requestModel.getActivityTypeId());
		activity.setActivityTypes(activityType);

		activity.setCreatedAt(dateNow);
		activity.setUpdatedAt(dateNow);

		Activities newActivity = activitiesRepository.save(activity);

		Set<ActivityParticipant> activityParticipantSet = requestModel.getParticipantsIds().stream()
				.map(participantId -> {
					ActivityParticipant activityParticipant = new ActivityParticipant();
					People people = peopleService.get(participantId);

					activityParticipant.setPeople(people);
					activityParticipant.setActivities(newActivity);

					return activitiesParticipantRepository.save(activityParticipant);
				}).collect(Collectors.toSet());

		newActivity.setActivityParticipant(activityParticipantSet);

		Set<ActivityCollaborator> activityCollaboratorSet = requestModel.getCollaboratorsIds().stream()
				.map(collaboratorId -> {
					ActivityCollaborator activityCollaborator = new ActivityCollaborator();
					Users collaborator = usersService.get(collaboratorId);

					activityCollaborator.setUsers(collaborator);
					activityCollaborator.setActivities(newActivity);

					return activitiesCollaboratorRepository.save(activityCollaborator);
				}).collect(Collectors.toSet());

		newActivity.setActivityCollaborator(activityCollaboratorSet);

		activitiesRepository.save(newActivity);

		return populateActivityResponse(newActivity);
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

	public ActivityResponse update(Long id, ActivitiesRequest requestModel) {
		Date dateNow = new Date();
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
		Statuses status = statusesService.findById(requestModel.getStatusId());
		activity.setStatuses(status);

		ActivityTypes activityType = activityTypesRepository.getById(requestModel.getActivityTypeId());
		activity.setActivityTypes(activityType);

		activity.setUpdatedAt(dateNow);

		Set<ActivityParticipant> activityParticipantSet = requestModel.getParticipantsIds().stream()
				.map(participantId -> {
					ActivityParticipant activityParticipant = new ActivityParticipant();
					People people = peopleService.get(participantId);

					activityParticipant.setPeople(people);
					activityParticipant.setActivities(activity);

					return activitiesParticipantRepository.save(activityParticipant);
				}).collect(Collectors.toSet());

		activity.setActivityParticipant(activityParticipantSet);

		Set<ActivityCollaborator> activityCollaboratorSet = requestModel.getCollaboratorsIds().stream()
				.map(collaboratorId -> {
					ActivityCollaborator activityCollaborator = new ActivityCollaborator();
					Users collaborator = usersService.get(collaboratorId);

					activityCollaborator.setUsers(collaborator);
					activityCollaborator.setActivities(activity);

					return activitiesCollaboratorRepository.save(activityCollaborator);
				}).collect(Collectors.toSet());

		activity.setActivityCollaborator(activityCollaboratorSet);

		activitiesRepository.save(activity);

		return populateActivityResponse(activity);
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
		activityResponse.setStartDate(activity.getStartedAt());
		activityResponse.setEndDate(activity.getEndedAt());
		activityResponse.setStatus(activity.getStatuses());

		BeanUtils.copyProperties(activity, activityResponse);

		OwnerResponse ownerResponse = new OwnerResponse();
		BeanUtils.copyProperties(activity.getUsers(), ownerResponse);
		activityResponse.setCreatedBy(ownerResponse);

		return activityResponse;
	}
}
