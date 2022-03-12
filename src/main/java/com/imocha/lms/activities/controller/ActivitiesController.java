package com.imocha.lms.activities.controller;

import javax.validation.Valid;

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

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.activities.model.ActivitiesRequest;
import com.imocha.lms.activities.model.ActivityResponse;
import com.imocha.lms.activities.service.ActivitiesService;

@RestController
@RequestMapping("activities")
public class ActivitiesController {

	@Autowired
	private ActivitiesService activitiesService;

	@GetMapping("page")
	public Page<ActivityResponse> page(@Valid PageableRequest pageableRequest) {
		return activitiesService.page(pageableRequest);
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
	public ActivityResponse add(@RequestBody ActivitiesRequest activitiesRequest) {
		return activitiesService.addActiviy(activitiesRequest);
	}

	@DeleteMapping("{id}")
	public Long delete(Long id) {
		return activitiesService.deleteById(id);
	}

}