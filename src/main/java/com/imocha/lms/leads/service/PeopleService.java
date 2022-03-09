package com.imocha.lms.leads.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.model.ActivityResponse;
import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.common.model.EmailResponse;
import com.imocha.lms.common.model.PhoneResponse;
import com.imocha.lms.common.model.TagResponse;
import com.imocha.lms.common.service.ActivitiesService;
import com.imocha.lms.common.service.EmailService;
import com.imocha.lms.common.service.FollowersService;
import com.imocha.lms.common.service.PhoneService;
import com.imocha.lms.common.service.TagService;
import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.service.DealsService;
import com.imocha.lms.entities.Activities;
import com.imocha.lms.entities.Followers;
import com.imocha.lms.entities.PersonOrganization;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.DealsResponse;
import com.imocha.lms.leads.model.FollowerResponse;
import com.imocha.lms.leads.model.OrganizationResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.PeopleListResponse;
import com.imocha.lms.leads.model.PersonListResponse;
import com.imocha.lms.leads.model.PersonPageResponse;
import com.imocha.lms.leads.repositories.PeopleRepository;
import com.imocha.lms.users.entities.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PeopleService {

	@Autowired
	private PeopleRepository peopleRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PhoneService phoneService;

	@Autowired
	private TagService tagService;

	@Autowired
	private PersonOrganizationService personOrganizationService;

	@Autowired
	private DealsService dealsService;

	@Autowired
	private ActivitiesService activitiesService;

	@Autowired
	private FollowersService followersService;

	public Page<PersonPageResponse> page(PageableRequest pageableRequest) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
		Page<People> peoplePage = this.peopleRepository.findAll(pageRequest);

		List<PersonPageResponse> personResponseList = peoplePage.getContent().stream().map(people -> {
			return generatePersonResponse(people);
		}).collect(Collectors.toList());

		Page<PersonPageResponse> personResponsePageImpl = new PageImpl<>(personResponseList, pageRequest,
				peoplePage.getTotalElements());
		return personResponsePageImpl;
	}

	public List<People> listByName(PageableRequest pageableRequest, String name) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);

		List<People> peoplePage = peopleRepository.findByNameIgnoreCaseContaining(name);

		return peoplePage;
	}

	public PersonPageResponse findById(Long id) {
		Optional<People> peopleOptional = peopleRepository.findById(id);
		PersonPageResponse personResponse = new PersonPageResponse();

		if (!peopleOptional.isEmpty()) {
			People people = peopleOptional.get();

			personResponse = generatePersonResponse(people);
		}

		return personResponse;
	}

	public List<PeopleListResponse> listAll() {
		List<People> peoples = peopleRepository.findAll();

		List<PeopleListResponse> peopleList = peoples.stream().map(people -> {
			PeopleListResponse peopleResponse = new PeopleListResponse();
			BeanUtils.copyProperties(people, peopleResponse);

			return peopleResponse;
		}).collect(Collectors.toList());

		return peopleList;
	}

	public PersonPageResponse generatePersonResponse(People people) {
		Long id = people.getId();

		PersonPageResponse personResponse = new PersonPageResponse();

		ContactTypesResponse contactTypes = new ContactTypesResponse();
		BeanUtils.copyProperties(people.getContactTypes(), contactTypes);

		BeanUtils.copyProperties(people, personResponse);
		personResponse.setContactTypes(contactTypes);

		List<EmailResponse> emails = emailService.getPersonEmailById(id);
		personResponse.setEmails(emails);

		List<PhoneResponse> phones = phoneService.getPersonPhoneById(id);
		personResponse.setPhones(phones);

		OwnerResponse owner = new OwnerResponse();
		BeanUtils.copyProperties(people.getOwner(), owner);

		personResponse.setOwner(owner);

		List<PersonOrganization> personOrganizations = personOrganizationService.findByPeopleId(id);

		List<OrganizationResponse> organizationResponses = personOrganizations.stream().map(personOrganization -> {
			OrganizationResponse orResponse = new OrganizationResponse();

			orResponse.setId(personOrganization.getOrganizations().getId());
			orResponse.setName(personOrganization.getOrganizations().getName());
			orResponse.setJobTitle(personOrganization.getJobTitle());

			ContactTypesResponse organizationContactTypes = new ContactTypesResponse();
			BeanUtils.copyProperties(personOrganization.getOrganizations().getContactTypes(), organizationContactTypes);

			orResponse.setContactTypes(organizationContactTypes);

			return orResponse;
		}).collect(Collectors.toList());

		Long openDeals = dealsService.getPersonOpenDealsCountByStatus(id);
		Long closedDeals = dealsService.getPersonClosedDealsCountByStatus(id);

		personResponse.setClosedDealsCount(closedDeals);
		personResponse.setOpenDealsCount(openDeals);

		personResponse.setOrganizations(organizationResponses);

		List<TagResponse> tags = tagService.getPersonTagById(id);
		personResponse.setTags(tags);

		return personResponse;
	}

	public List<ActivityResponse> getPersonActivitiesByPersonId(Long id) {
		List<Activities> activities = activitiesService.getActivitiesByPersonId(id);

		List<ActivityResponse> activityRes = activities.stream().map(activity -> {
			ActivityResponse activityResponse = new ActivityResponse();
			List<People> peoples = activitiesService.getParticipantsByActivityId(activity);
			List<Users> collaborators = activitiesService.getCollaboratorsByActivity(activity);

			activityResponse.setCollaborators(collaborators);
			activityResponse.setParticipants(peoples);
			activityResponse.setId(activity.getId());
			activityResponse.setTitle(activity.getTitle());
			activityResponse.setDescription(activity.getDescription());

			OwnerResponse owner = new OwnerResponse();
			BeanUtils.copyProperties(activity.getUsers(), owner);
			activityResponse.setCreatedBy(owner);

			return activityResponse;
		}).collect(Collectors.toList());

		return activityRes;
	}

	public Page<FollowerResponse> getFollowerByPersonId(Long id, PageableRequest pageableRequest) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
		Page<Followers> followerPage = followersService.getFollowersByPersonId(id, pageRequest);

		List<FollowerResponse> followerResponses = followerPage.getContent().stream().map(follower -> {
			FollowerResponse followerRes = new FollowerResponse();

			Long openDeals = dealsService.getPersonOpenDealsCountByStatus(follower.getPeople().getId());
			Long closedDeals = dealsService.getPersonClosedDealsCountByStatus(follower.getPeople().getId());

			OwnerResponse owner = new OwnerResponse();
			BeanUtils.copyProperties(follower.getPeople().getOwner(), owner);

			ContactTypesResponse contactTypes = new ContactTypesResponse();
			BeanUtils.copyProperties(follower.getPeople().getContactTypes(), contactTypes);

			List<TagResponse> tags = tagService.getPersonTagById(follower.getPeople().getId());

			followerRes.setTags(tags);
			followerRes.setOwner(owner);
			followerRes.setName(follower.getPeople().getName());
			followerRes.setClosedDealsCount(closedDeals);
			followerRes.setOpenDealsCount(openDeals);
			followerRes.setId(follower.getId());
			followerRes.setContactTypes(contactTypes);

			return followerRes;
		}).collect(Collectors.toList());

		Page<FollowerResponse> followerResponsePageImpl = new PageImpl<>(followerResponses, pageRequest,
				followerPage.getTotalElements());
		return followerResponsePageImpl;
	}

	public List<DealsResponse> getDealsByPersonId(Long id) {

		List<Deals> deals = dealsService.getDealsByPersonId(id);
		List<DealsResponse> dealsResponses = deals.stream().map(deal -> {
			DealsResponse dealsRes = new DealsResponse();
			BeanUtils.copyProperties(deal, dealsRes);

			OwnerResponse owner = new OwnerResponse();
			BeanUtils.copyProperties(deal.getOwner(), owner);

			dealsRes.setOwner(owner);

			return dealsRes;
		}).collect(Collectors.toList());

		return dealsResponses;
	}

	public List<PersonListResponse> list() {
		List<People> list = peopleRepository.findAll();

		List<PersonListResponse> response = new ArrayList<PersonListResponse>();
		for (People people : list) {
			PersonListResponse personListResponse = this.mapPeopleToPersonListResponse(people);
			response.add(personListResponse);
		}
		return response;
	}

	private PersonListResponse mapPeopleToPersonListResponse(People people) {
		PersonListResponse response = new PersonListResponse();
		BeanUtils.copyProperties(people, response);
		return response;
	}
}
