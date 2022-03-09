package com.imocha.lms.leads.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.model.ActivityResponse;
import com.imocha.lms.leads.model.DealsResponse;
import com.imocha.lms.leads.model.FollowerResponse;
import com.imocha.lms.leads.model.PersonPageResponse;
import com.imocha.lms.leads.service.PeopleService;

@RestController
@RequestMapping("person")
public class PersonController {

	@Autowired
	private PeopleService peopleService;

	@GetMapping("list")
	public Page<PersonPageResponse> list(@Valid PageableRequest pageableRequest) {
		return peopleService.page(pageableRequest);
	}

	@GetMapping("/{id}")
	public PersonPageResponse getById(@PathVariable("id") Long id) {
		return peopleService.findById(id);
	}

	@GetMapping("{id}/activities/list")
	public List<ActivityResponse> getPersonActivitiesById(@PathVariable("id") Long id) {
		return peopleService.getPersonActivitiesByPersonId(id);
	}

	@GetMapping("{id}/followers/list")
	public Page<FollowerResponse> listFollowers(@Valid PageableRequest pageableRequest, @RequestParam("id") Long id) {
		return peopleService.getFollowerByPersonId(id, pageableRequest);
	}

	@GetMapping("{id}/deals/list")
	public List<DealsResponse> listDeals(@PathVariable("id") Long id) {
		return peopleService.getDealsByPersonId(id);
	}

	@GetMapping("page")
	public Page<PersonPageResponse> page(@Valid PageableRequest pageableRequest) {
		return peopleService.page(pageableRequest);
	}

}
