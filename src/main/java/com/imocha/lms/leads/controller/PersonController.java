package com.imocha.lms.leads.controller;

import java.util.List;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.model.ActivityResponse;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.DealsResponse;
import com.imocha.lms.leads.model.FollowerResponse;
import com.imocha.lms.leads.model.PeopleRequest;
import com.imocha.lms.leads.model.PersonListResponse;
import com.imocha.lms.leads.model.PersonPageResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

	@Autowired
	private PeopleService peopleService;

	@GetMapping("list")
	public List<PersonListResponse> list() {
		return peopleService.list();
	}

	@GetMapping("/{id}")
	public PersonPageResponse getById(@PathVariable("id") Long id) {
		return peopleService.findById(id);
	}

	@GetMapping("{id}/activities/list")
	public List<ActivityResponse> getPersonActivitiesById(@PathVariable("id") Long id) {
		return peopleService.getPersonActivitiesByPersonId(id);
	}

	@GetMapping("{id}/followers/page")
	public Page<FollowerResponse> listFollowers(@Valid PageableRequest pageableRequest, @RequestParam("id") Long id) {
		return peopleService.getFollowersByPersonId(id, pageableRequest);
	}

	@GetMapping("{id}/deals/list")
	public List<DealsResponse> listDeals(@PathVariable("id") Long id) {
		return peopleService.getDealsByPersonId(id);
	}

	@GetMapping("page")
	public Page<PersonPageResponse> page(@Valid PageableRequest pageableRequest) {
		return peopleService.page(pageableRequest);
	}

	@DeleteMapping("/{id}")
	public Long delete(@PathVariable("id") Long id) {
		return peopleService.deleteByPersonId(id);
	}

	@PostMapping("add")
	public People add(@RequestBody PeopleRequest peopleRequest) {
		return peopleService.addPeople(peopleRequest);
	}

	@PutMapping("/{id}")
	public People update(@RequestBody PeopleRequest peopleRequest, @PathVariable("id") Long id) {
		return peopleService.updatePeople(id, peopleRequest);
	}

}
