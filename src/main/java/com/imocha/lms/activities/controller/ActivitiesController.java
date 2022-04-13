package com.imocha.lms.activities.controller;

import java.util.List;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.activities.model.ActivitiesRequest;
import com.imocha.lms.activities.model.ActivityListResponse;
import com.imocha.lms.activities.model.ActivityPageResponse;
import com.imocha.lms.activities.model.ActivityResponse;
import com.imocha.lms.activities.service.ActivitiesService;
import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.leads.model.ActivityTypeResponse;

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
@RequestMapping("activities")
public class ActivitiesController {

	@Autowired
	private ActivitiesService activitiesService;

	@GetMapping("page")
	public Page<ActivityPageResponse> page(@Valid PageableRequest pageableRequest) {
		return activitiesService.page(pageableRequest);
	}

	@GetMapping("/{id}")
	public ActivityResponse get(@PathVariable("id") long id) {
		return activitiesService.get(id);
	}

	@GetMapping("list")
	public List<ActivityListResponse> list(@RequestParam(required = false) ContextableTypes contextableType) {
		return activitiesService.list(contextableType);
	}

	@GetMapping("activityTypes/list")
	public List<ActivityTypeResponse> listActivityTypes() {
		return activitiesService.listActivityTypes();
	}

	@GetMapping("done")
	public List<ActivityListResponse> done() {
		return activitiesService.done();
	}

	@PutMapping("{id}/done")
	public ActivityResponse markAsDone(@PathVariable("id") Long id) {
		return activitiesService.markAsDone(id);
	}

	@PutMapping("{id}")
	public ActivityResponse update(@RequestBody ActivitiesRequest activitiesRequest, @PathVariable("id") Long id) {
		return activitiesService.update(id, activitiesRequest);
	}

	@PostMapping("add")
	public long add(@RequestBody ActivitiesRequest activitiesRequest) {
		return activitiesService.addActiviy(activitiesRequest);
	}

	@DeleteMapping("{id}")
	public Long delete(@PathVariable("id") Long id) {
		return activitiesService.deleteById(id);
	}

}
