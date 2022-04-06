package com.imocha.lms.leads.controller;

import java.util.List;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.activities.model.ActivityPageResponse;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.LeadDealsResponse;
import com.imocha.lms.leads.model.FollowerResponse;
import com.imocha.lms.leads.model.PeopleRequest;
import com.imocha.lms.leads.model.PersonListResponse;
import com.imocha.lms.leads.model.PersonPageResponse;
import com.imocha.lms.leads.model.PersonResponse;
import com.imocha.lms.leads.model.TagRequest;
import com.imocha.lms.leads.model.UpdateAddressRequest;
import com.imocha.lms.leads.model.UpdateContactRequestModel;
import com.imocha.lms.leads.model.UpdateDetailsRequest;
import com.imocha.lms.leads.model.UpdateFollowerRequest;
import com.imocha.lms.leads.model.UpdatePersonOrganizationRequestModel;
import com.imocha.lms.leads.service.PeopleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

	@Autowired
	private PeopleService peopleService;

	@GetMapping("search")
	public Page<PersonPageResponse> search(@Valid PageableRequest pageableRequest) {
		return peopleService.criteriaSearch(pageableRequest);
	}

	@GetMapping("list")
	public List<PersonListResponse> list() {
		return peopleService.list();
	}

	@GetMapping("/{id}")
	public PersonResponse getById(@PathVariable("id") Long id) {
		return peopleService.findById(id);
	}

	@GetMapping("{id}/activities/list")
	public List<ActivityPageResponse> getPersonActivitiesById(@PathVariable("id") Long id) {
		return peopleService.getPersonActivitiesByPersonId(id);
	}

	@GetMapping("{id}/followers/page")
	public Page<FollowerResponse> listFollowers(@Valid PageableRequest pageableRequest, @PathVariable("id") Long id) {
		return peopleService.getFollowersByPersonId(id, pageableRequest);
	}

	@GetMapping("{id}/deals/list")
	public List<LeadDealsResponse> listDeals(@PathVariable("id") Long id) {
		return peopleService.getDealsByPersonId(id);
	}

	@GetMapping("page")
	public Page<PersonPageResponse> page(@Valid PageableRequest pageableRequest) {
		return peopleService.page(pageableRequest);
	}

	@GetMapping("{id}/deals/page")
	public Page<LeadDealsResponse> getDealsPage(@Valid PageableRequest pageableRequest, @PathVariable("id") Long id) {
		return peopleService.getDealsPage(pageableRequest, id);
	}

	@DeleteMapping("/{id}")
	public Long delete(@PathVariable("id") Long id) {
		return peopleService.deleteByPersonId(id);
	}

	@DeleteMapping("/{id}/tag")
	public Long deleteTag(@RequestBody TagRequest requestModel, @PathVariable("id") Long id) {
		return peopleService.deleteTag(requestModel, id);
	}

	@PostMapping("add")
	public People add(@RequestBody PeopleRequest peopleRequest) {
		return peopleService.addPeople(peopleRequest);
	}

	@PostMapping("/{id}/tag")
	public People addTag(@RequestBody TagRequest requestModel, @PathVariable("id") Long id) {
		return peopleService.addTag(requestModel, id);
	}

	@PutMapping("/{id}")
	public People update(@RequestBody PeopleRequest peopleRequest, @PathVariable("id") Long id) {
		return peopleService.updatePeople(id, peopleRequest);
	}

	@PutMapping("/{id}/address")
	public People updateAddress(@RequestBody UpdateAddressRequest requestModel, @PathVariable("id") Long id) {
		return peopleService.updateAddress(requestModel, id);
	}

	@PutMapping("/{id}/details")
	public People updateDetails(@RequestBody UpdateDetailsRequest requestModel, @PathVariable("id") Long id) {
		return peopleService.updateDetails(requestModel, id);
	}

	@PutMapping("/{id}/contact")
	public People updateAddress(@RequestBody UpdateContactRequestModel requestModel, @PathVariable("id") Long id) {
		return peopleService.updateContact(requestModel, id);
	}

	@PutMapping("/{id}/followers")
	public People updateAddress(@RequestBody UpdateFollowerRequest requestModel, @PathVariable("id") Long id) {
		return peopleService.updateFollowers(requestModel, id);
	}

	@PutMapping("/{id}/organizations")
	public People updateAddress(@RequestBody UpdatePersonOrganizationRequestModel requestModel,
			@PathVariable("id") Long id) {
		return peopleService.updateOrganizations(requestModel, id);
	}

}
