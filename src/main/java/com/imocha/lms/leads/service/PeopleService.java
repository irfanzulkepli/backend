package com.imocha.lms.leads.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.entities.Countries;
import com.imocha.lms.common.entities.Emails;
import com.imocha.lms.common.entities.Followers;
import com.imocha.lms.common.entities.PhoneEmailTypes;
import com.imocha.lms.common.entities.Phones;
import com.imocha.lms.common.model.ActivityResponse;
import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.common.model.EmailResponse;
import com.imocha.lms.common.model.PhoneResponse;
import com.imocha.lms.common.model.TagResponse;
import com.imocha.lms.common.service.ActivitiesService;
import com.imocha.lms.common.service.CountriesService;
import com.imocha.lms.common.service.EmailService;
import com.imocha.lms.common.service.FollowersService;
import com.imocha.lms.common.service.PhoneEmailService;
import com.imocha.lms.common.service.PhoneService;
import com.imocha.lms.common.service.TagService;
import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.service.DealsService;
import com.imocha.lms.entities.Activities;
import com.imocha.lms.entities.ActivityTypes;
import com.imocha.lms.leads.entities.ContactTypes;
import com.imocha.lms.leads.entities.Organizations;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.entities.PersonOrganization;
import com.imocha.lms.leads.model.ActivityTypeResponse;
import com.imocha.lms.leads.model.CollaboratorResponse;
import com.imocha.lms.leads.model.ContactRequest;
import com.imocha.lms.leads.model.DealsResponse;
import com.imocha.lms.leads.model.FollowerResponse;
import com.imocha.lms.leads.model.OrganizationResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.ParticipantResponse;
import com.imocha.lms.leads.model.PeopleListResponse;
import com.imocha.lms.leads.model.PeopleRequest;
import com.imocha.lms.leads.model.PersonListResponse;
import com.imocha.lms.leads.model.PersonOrganizationRequest;
import com.imocha.lms.leads.model.PersonPageResponse;
import com.imocha.lms.leads.repositories.PeopleRepository;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.service.UsersService;

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

	@Lazy
	@Autowired
	private DealsService dealsService;

	@Autowired
	private ActivitiesService activitiesService;

	@Autowired
	private FollowersService followersService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private ContactTypesService contactTypesService;

	@Autowired
	private PhoneEmailService phoneEmailService;

	@Autowired
	private CountriesService countriesService;

	public Page<PersonPageResponse> page(PageableRequest pageableRequest) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
		Page<People> peoplePage = this.peopleRepository.findByActiveTrue(pageRequest);

		List<PersonPageResponse> personResponseList = peoplePage.getContent().stream().map(people -> {
			return generatePersonResponse(people);
		}).collect(Collectors.toList());

		Page<PersonPageResponse> personResponsePageImpl = new PageImpl<>(personResponseList, pageRequest,
				peoplePage.getTotalElements());
		return personResponsePageImpl;
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

		personResponse.setCountry(people.getCountries());

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
			List<People> pariticipants = activitiesService.getParticipantsByActivityId(activity);
			List<Users> collaborators = activitiesService.getCollaboratorsByActivity(activity);

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
			activityResponse.setId(activity.getId());
			activityResponse.setTitle(activity.getTitle());
			activityResponse.setDescription(activity.getDescription());
			activityResponse.setStartDate(activity.getStartedAt());
			activityResponse.setStartTime(activity.getStartTime());
			activityResponse.setEndDate(activity.getEndedAt());
			activityResponse.setEndTime(activity.getEndTime());

			System.out.println("endedTime: " + activity.getEndTime());

			OwnerResponse owner = new OwnerResponse();
			BeanUtils.copyProperties(activity.getUsers(), owner);
			activityResponse.setCreatedBy(owner);

			return activityResponse;
		}).collect(Collectors.toList());

		return activityRes;
	}

	public Page<FollowerResponse> getFollowersByPersonId(Long id, PageableRequest pageableRequest) {
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

	public Long deleteByPersonId(Long id) {
		Optional<People> peopleOptional = peopleRepository.findById(id);

		if (!peopleOptional.isEmpty()) {
			People people = peopleOptional.get();

			if (!people.isActive()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
			} else {
				people.setActive(false);

				peopleRepository.save(people);
				personOrganizationService.delete(id);

				return people.getId();
			}

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
	}

	public People addPeople(PeopleRequest peopleRequest) {
		People people = new People();
		return populatePeopleDetails(people, peopleRequest, false);
	}

	public People updatePeople(Long id, PeopleRequest peopleRequest) {
		Optional<People> peopleOptional = peopleRepository.findById(id);

		if (peopleOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		People people = peopleOptional.get();

		return populatePeopleDetails(people, peopleRequest, true);
	}

	private People populatePeopleDetails(People people, PeopleRequest peopleRequest, Boolean isEdit) {
		Date dateNow = new Date();

		if (!isEdit) {
			people.setCreatedAt(dateNow);
		}

		people.setUpdatedAt(dateNow);

		BeanUtils.copyProperties(peopleRequest, people);

		List<PersonOrganizationRequest> organizationRequests = peopleRequest.getPersonOrganizationRequests();
		List<ContactRequest> emailsRequest = peopleRequest.getEmails();
		List<ContactRequest> phonesRequest = peopleRequest.getPhones();

		Users owner = usersService.get(peopleRequest.getOwnerId());
		people.setOwner(owner);

		Users createdBy = usersService.get(peopleRequest.getCreatedBy());
		people.setCreatedBy(createdBy);

		ContactTypes contactTypes = contactTypesService.get(peopleRequest.getContactTypesId());
		people.setContactTypes(contactTypes);

		if ((peopleRequest.getCountryId() != null)) {
			Countries country = countriesService.getCountryById(peopleRequest.getCountryId());
			people.setCountries(country);
		}

		People newPeople = peopleRepository.save(people);

		emailsRequest.forEach(emailRequest -> {
			Emails email = new Emails();
			System.out.println("email: " + email);

			BeanUtils.copyProperties(emailsRequest, email);
			email.setContextableType(emailRequest.getContextableType());
			email.setContextableId(newPeople.getId());
			email.setCreatedAt(dateNow);
			email.setUpdatedAt(dateNow);
			email.setValue(emailRequest.getValue());

			if ((emailRequest.getTypeId() != null)) {
				PhoneEmailTypes phoneEmailTypes = phoneEmailService.get(emailRequest.getTypeId());
				email.setContactTypes(phoneEmailTypes);
			}

			emailService.save(email);
		});

		phonesRequest.forEach(phoneRequest -> {
			Phones phone = new Phones();
			System.out.println("phone: " + phone);

			BeanUtils.copyProperties(phoneRequest, phone);
			phone.setContextableType(phoneRequest.getContextableType());
			phone.setContextableId(newPeople.getId());
			phone.setCreatedAt(dateNow);
			phone.setUpdatedAt(dateNow);
			phone.setValue(phoneRequest.getValue());

			if ((phoneRequest.getTypeId() != null)) {
				PhoneEmailTypes phoneEmailTypes = phoneEmailService.get(phoneRequest.getTypeId());
				phone.setContactTypes(phoneEmailTypes);
			}

			phoneService.save(phone);
		});

		peopleRepository.save(people);

		organizationRequests.forEach(organizationRequest -> {
			PersonOrganization personOrganization = new PersonOrganization();

			Organizations org = organizationService.getOrganizationById(organizationRequest.getId());

			personOrganization.setJobTitle(organizationRequest.getJobTitle());
			personOrganization.setOrganizations(org);
			personOrganization.setPeople(newPeople);

			personOrganizationService.save(personOrganization);
		});

		return people;
	}

	public People get(long id) {
		Optional<People> pOptional = peopleRepository.findById(id);
		if (!pOptional.isPresent()) {
			// TODO: throw error
		}

		return pOptional.get();
	}
}
