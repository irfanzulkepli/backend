package com.imocha.lms.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.common.repositories.ActivitiesRepository;
import com.imocha.lms.entities.Activities;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.repositories.PeopleRepository;
import com.imocha.lms.users.entities.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActivitiesService {

	@Autowired
	ActivitiesRepository activitiesRepository;

	@Autowired
	PeopleRepository peopleRepository;

	public List<Activities> getActivitiesByPersonId(Long id) {
		List<Activities> activities = activitiesRepository
				.findByContextableTypeIgnoreCaseContainingAndContextableId("person", id);

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
}
