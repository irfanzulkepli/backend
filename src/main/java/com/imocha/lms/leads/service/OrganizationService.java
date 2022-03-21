package com.imocha.lms.leads.service;

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
import com.imocha.lms.activities.entities.Activities;
import com.imocha.lms.activities.entities.ActivityTypes;
import com.imocha.lms.activities.model.ActivityResponse;
import com.imocha.lms.activities.service.ActivitiesService;
import com.imocha.lms.common.entities.Countries;
import com.imocha.lms.common.entities.Followers;
import com.imocha.lms.common.entities.Taggables;
import com.imocha.lms.common.entities.Tags;
import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.common.model.TagResponse;
import com.imocha.lms.common.service.CountriesService;
import com.imocha.lms.common.service.FollowersService;
import com.imocha.lms.common.service.TagService;
import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.service.DealsService;
import com.imocha.lms.leads.entities.ContactTypes;
import com.imocha.lms.leads.entities.Organizations;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.entities.PersonOrganization;
import com.imocha.lms.leads.model.ActivityTypeResponse;
import com.imocha.lms.leads.model.CollaboratorResponse;
import com.imocha.lms.leads.model.DealsResponse;
import com.imocha.lms.leads.model.FollowerResponse;
import com.imocha.lms.leads.model.OrganizationResponse;
import com.imocha.lms.leads.model.OrganizationsRequest;
import com.imocha.lms.leads.model.OrganizationsResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.ParticipantResponse;
import com.imocha.lms.leads.model.PersonOrganizationRequest;
import com.imocha.lms.leads.model.PersonsResponse;
import com.imocha.lms.leads.model.TagRequest;
import com.imocha.lms.leads.model.UpdateAddressRequest;
import com.imocha.lms.leads.model.UpdateDetailsRequest;
import com.imocha.lms.leads.model.UpdateFollowerRequest;
import com.imocha.lms.leads.model.UpdatePersonOrganizationRequestModel;
import com.imocha.lms.leads.repositories.OrganizationRepository;
import com.imocha.lms.users.entities.Users;
import com.imocha.lms.users.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrganizationService {

	Date dateNow = new Date();

	@Lazy
	@Autowired
	private ActivitiesService activitiesService;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private PeopleService peopleService;

	@Lazy
	@Autowired
	private DealsService dealsService;

	@Autowired
	private FollowersService followersService;

	@Autowired
	private TagService tagService;

	@Autowired
	private PersonOrganizationService personOrganizationService;

	@Autowired
	private ContactTypesService contactTypesService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private CountriesService countriesService;

	public Page<OrganizationsResponse> page(PageableRequest pageableRequest) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
		Page<Organizations> organizationPages = organizationRepository.findByActiveTrue(pageRequest);

		List<OrganizationsResponse> organizations = organizationPages.getContent().stream().map(organization -> {
			return populateOrganizationResponse(organization);
		}).collect(Collectors.toList());

		Page<OrganizationsResponse> organizationResponsePageImpl = new PageImpl<>(organizations, pageRequest,
				organizationPages.getTotalElements());
		return organizationResponsePageImpl;
	}

	public OrganizationsResponse getById(Long id) {
		Optional<Organizations> organizationOptional = organizationRepository.findById(id);

		if (organizationOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Organizations organization = organizationOptional.get();

		if (!organization.isActive()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		return populateOrganizationResponse(organization);
	}

	public Organizations add(OrganizationsRequest requestModel) {
		Organizations organization = new Organizations();
		BeanUtils.copyProperties(requestModel, organization);

		Users owner = usersService.get(requestModel.getOwnerId());
		organization.setOwner(owner);

		Users createdBy = usersService.get(requestModel.getCreatedById());
		organization.setUsers(createdBy);

		ContactTypes contactTypes = contactTypesService.findById(requestModel.getContactTypesId());
		organization.setContactTypes(contactTypes);

		if ((requestModel.getCountryId() != null)) {
			Countries country = countriesService.getCountryById(requestModel.getCountryId());
			organization.setCountries(country);
		}

		organization.setCreatedAt(dateNow);
		organization.setUpdatedAt(dateNow);

		return organizationRepository.save(organization);
	}

	public Organizations update(OrganizationsRequest requestModel, Long id) {
		Optional<Organizations> organizationOptional = organizationRepository.findById(id);

		if (organizationOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Organizations organization = organizationOptional.get();
		BeanUtils.copyProperties(requestModel, organization);

		Users owner = usersService.get(requestModel.getOwnerId());
		organization.setOwner(owner);

		Users createdBy = usersService.get(requestModel.getCreatedById());
		organization.setUsers(createdBy);

		ContactTypes contactTypes = contactTypesService.findById(requestModel.getContactTypesId());
		organization.setContactTypes(contactTypes);

		if ((requestModel.getCountryId() != null)) {
			Countries country = countriesService.getCountryById(requestModel.getCountryId());
			organization.setCountries(country);
		}

		organization.setUpdatedAt(dateNow);

		return organizationRepository.save(organization);
	}

	public Long delete(Long id) {
		Optional<Organizations> organizationOptional = organizationRepository.findById(id);

		if (organizationOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Organizations organization = organizationOptional.get();

		if (!organization.isActive()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		organization.setActive(false);

		organizationRepository.save(organization);
		personOrganizationService.deleteByOrganizationId(id);

		return id;
	}

	public List<OrganizationResponse> list() {
		List<Organizations> organizations = organizationRepository.findByActiveTrue();

		List<OrganizationResponse> organizationResponseList = organizations.stream().map(organization -> {
			OrganizationResponse organizationResponse = new OrganizationResponse();
			BeanUtils.copyProperties(organization, organizationResponse);

			ContactTypesResponse contactTypes = new ContactTypesResponse();
			BeanUtils.copyProperties(organization.getContactTypes(), contactTypes);
			organizationResponse.setContactTypes(contactTypes);

			return organizationResponse;
		}).collect(Collectors.toList());

		return organizationResponseList;
	}

	public List<ActivityResponse> getOrganizationActivitiesById(Long id) {
		List<Activities> activities = activitiesService.getActivitiesByLeadId(id, "organization");

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
			activityResponse.setStatus(activity.getStatuses());

			OwnerResponse owner = new OwnerResponse();
			BeanUtils.copyProperties(activity.getUsers(), owner);
			activityResponse.setCreatedBy(owner);

			return activityResponse;
		}).collect(Collectors.toList());

		return activityRes;
	}

	public Page<FollowerResponse> getFollowersByOrganizationId(Long id, PageableRequest pageableRequest) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
		Page<Followers> followerPage = followersService.getFollowersByLeadsId(id, "organization", pageRequest);

		List<FollowerResponse> followerResponses = followerPage.getContent().stream().map(follower -> {
			FollowerResponse followerRes = new FollowerResponse();

			Long openDeals = dealsService.getLeadsOpenDealsCountByStatus(follower.getPeople().getId(), "person");
			Long closedDeals = dealsService.getLeadsClosedDealsCountByStatus(follower.getPeople().getId(), "person");

			OwnerResponse owner = new OwnerResponse();
			BeanUtils.copyProperties(follower.getPeople().getOwner(), owner);

			ContactTypesResponse contactTypes = new ContactTypesResponse();
			BeanUtils.copyProperties(follower.getPeople().getContactTypes(), contactTypes);

			List<TagResponse> tags = tagService.getLeadsTagById(follower.getPeople().getId(), "person");

			followerRes.setTags(tags);
			followerRes.setOwner(owner);
			followerRes.setName(follower.getPeople().getName());
			followerRes.setClosedDealsCount(closedDeals);
			followerRes.setOpenDealsCount(openDeals);
			followerRes.setId(follower.getId());
			followerRes.setPeopleId(follower.getPeople().getId());
			followerRes.setContactTypes(contactTypes);

			return followerRes;
		}).collect(Collectors.toList());

		Page<FollowerResponse> followerResponsePageImpl = new PageImpl<>(followerResponses, pageRequest,
				followerPage.getTotalElements());
		return followerResponsePageImpl;
	}

	public List<DealsResponse> getDealsByOrganizationId(Long id) {

		List<Deals> deals = dealsService.getDealsByLeadId(id, "organization");
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

	public Organizations updateAddress(UpdateAddressRequest requestModel, Long id) {
		Optional<Organizations> organizationOptional = organizationRepository.findById(id);

		if (organizationOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Organizations organization = organizationOptional.get();

		BeanUtils.copyProperties(requestModel, organization);
		Countries country = countriesService.getCountryById(requestModel.getCountryId());

		organization.setCountries(country);
		organization.setUpdatedAt(dateNow);

		return organizationRepository.save(organization);
	}

	public Organizations updateDetails(UpdateDetailsRequest requestModel, Long id) {
		Optional<Organizations> organizationOptional = organizationRepository.findById(id);

		if (organizationOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Organizations organization = organizationOptional.get();

		BeanUtils.copyProperties(requestModel, organization);

		Users owner = usersService.get(requestModel.getOwnerId());
		organization.setOwner(owner);

		return organizationRepository.save(organization);
	}

	public Organizations updateFollowers(UpdateFollowerRequest requestModel, Long id) {
		Optional<Organizations> organizationOptional = organizationRepository.findById(id);

		if (organizationOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Organizations organization = organizationOptional.get();
		List<Followers> followers = followersService.getFollowersByLeadsId(id, "organization");
		followers.forEach(follower -> {
			followersService.deleteById(follower.getId());
		});

		requestModel.getFollowerIds().forEach(followerId -> {
			Followers newFollower = new Followers();
			People follower = peopleService.get(followerId);

			newFollower.setContextableId(organization.getId());
			newFollower.setContextableType("organization");
			newFollower.setCreatedAt(dateNow);
			newFollower.setUpdatedAt(dateNow);
			newFollower.setPeople(follower);

			followersService.save(newFollower);
		});

		return organization;
	}

	public Organizations updatePersons(UpdatePersonOrganizationRequestModel requestModel, Long id) {
		Optional<Organizations> organizationOptional = organizationRepository.findById(id);

		if (organizationOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Organizations organization = organizationOptional.get();

		handlePersonOrganizationUpdateRequest(requestModel.getPersonOrganizations(), organization);

		organization.setUpdatedAt(dateNow);
		organizationRepository.save(organization);

		return organization;
	}

	public Organizations addTag(TagRequest requestModel, Long id) {
		Optional<Organizations> organizationOptional = organizationRepository.findById(id);

		if (organizationOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Organizations organization = organizationOptional.get();

		Tags tag = tagService.getByTagId(requestModel.getId());
		Taggables taggable = new Taggables();
		taggable.setTaggableId(id);
		taggable.setTaggableType("person");
		taggable.setTags(tag);

		tagService.save(taggable);

		organization.setUpdatedAt(dateNow);
		organizationRepository.save(organization);

		return organization;
	}

	public Long deleteTag(TagRequest requestModel, Long id) {
		Optional<Organizations> organizationOptional = organizationRepository.findById(id);

		if (organizationOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		Organizations organization = organizationOptional.get();

		Tags tag = tagService.getByTagId(requestModel.getId());
		Taggables taggable = tagService.getTaggable(tag, id, "person");
		tagService.deleteByEntity(taggable);

		organization.setUpdatedAt(dateNow);
		organizationRepository.save(organization);

		return id;
	}

	public Organizations getOrganizationById(Long id) {
		Optional<Organizations> organizationOptional = organizationRepository.findById(id);

		if (organizationOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}

		return organizationOptional.get();
	}

	public List<PersonsResponse> getPersonsById(Long id) {
		List<PersonOrganization> personOrganizations = personOrganizationService.findByOrganizationId(id);
		List<PersonsResponse> personResponseList = personOrganizations.stream().map(personOrganization -> {
			PersonsResponse personsResponse = new PersonsResponse();
			ContactTypesResponse contactTypesResponse = new ContactTypesResponse();

			BeanUtils.copyProperties(personOrganization.getPeople().getContactTypes(), contactTypesResponse);
			BeanUtils.copyProperties(personOrganization.getPeople(), personsResponse);

			personsResponse.setContactTypes(contactTypesResponse);
			return personsResponse;
		}).collect(Collectors.toList());

		return personResponseList;
	}

	private OrganizationsResponse populateOrganizationResponse(Organizations organization) {
		OrganizationsResponse organizationResponse = new OrganizationsResponse();

		ContactTypesResponse contactTypes = new ContactTypesResponse();
		BeanUtils.copyProperties(organization.getContactTypes(), contactTypes);

		BeanUtils.copyProperties(organization, organizationResponse);
		organizationResponse.setContactTypes(contactTypes);

		OwnerResponse owner = new OwnerResponse();
		BeanUtils.copyProperties(organization.getOwner(), owner);

		organizationResponse.setCountry(organization.getCountries());

		organizationResponse.setOwner(owner);

		List<PersonOrganization> personOrganizations = personOrganizationService
				.findByOrganizationId(organization.getId());

		List<PersonsResponse> personResponses = personOrganizations.stream().map(personOrganization -> {
			PersonsResponse personResponse = new PersonsResponse();

			personResponse.setId(personOrganization.getPeople().getId());
			personResponse.setName(personOrganization.getPeople().getName());
			personResponse.setJobTitle(personOrganization.getJobTitle());

			ContactTypesResponse organizationContactTypes = new ContactTypesResponse();
			BeanUtils.copyProperties(personOrganization.getPeople().getContactTypes(), organizationContactTypes);

			personResponse.setContactTypes(organizationContactTypes);

			return personResponse;
		}).collect(Collectors.toList());

		Long openDeals = dealsService.getLeadsOpenDealsCountByStatus(organization.getId(), "organization");
		Long closedDeals = dealsService.getLeadsClosedDealsCountByStatus(organization.getId(), "organization");

		organizationResponse.setClosedDealsCount(closedDeals);
		organizationResponse.setOpenDealsCount(openDeals);
		organizationResponse.setCreatedAt(organization.getCreatedAt());
		organizationResponse.setPersons(personResponses);

		List<TagResponse> tags = tagService.getLeadsTagById(organization.getId(), "organization");
		organizationResponse.setTags(tags);

		return organizationResponse;
	}

	private void handlePersonOrganizationUpdateRequest(List<PersonOrganizationRequest> personOrganizationRequests,
			Organizations organization) {

		personOrganizationService.delete(organization.getId());
		personOrganizationRequests.forEach(peopleRequest -> {
			PersonOrganization personOrganization = new PersonOrganization();

			People people = peopleService.get(peopleRequest.getId());

			personOrganization.setJobTitle(peopleRequest.getJobTitle());
			personOrganization.setOrganizations(organization);
			personOrganization.setPeople(people);

			personOrganizationService.save(personOrganization);
		});
	}
}
