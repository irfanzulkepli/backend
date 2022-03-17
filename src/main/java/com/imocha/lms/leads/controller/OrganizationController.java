package com.imocha.lms.leads.controller;

import java.util.List;

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
import com.imocha.lms.activities.model.ActivityResponse;
import com.imocha.lms.leads.entities.Organizations;
import com.imocha.lms.leads.model.DealsResponse;
import com.imocha.lms.leads.model.FollowerResponse;
import com.imocha.lms.leads.model.OrganizationResponse;
import com.imocha.lms.leads.model.OrganizationsRequest;
import com.imocha.lms.leads.model.OrganizationsResponse;
import com.imocha.lms.leads.model.TagRequest;
import com.imocha.lms.leads.model.UpdateAddressRequest;
import com.imocha.lms.leads.model.UpdateDetailsRequest;
import com.imocha.lms.leads.model.UpdateFollowerRequest;
import com.imocha.lms.leads.model.UpdatePersonOrganizationRequestModel;
import com.imocha.lms.leads.repositories.OrganizationRepository;
import com.imocha.lms.leads.service.OrganizationService;

@RestController
@RequestMapping("organization")
public class OrganizationController {

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	OrganizationService organizationService;

	@GetMapping("page")
	public Page<OrganizationsResponse> page(@Valid PageableRequest pageableRequest) {
		return organizationService.page(pageableRequest);
	}

	@GetMapping("list")
	public List<OrganizationResponse> list() {
		return organizationService.list();
	}

	@GetMapping("{id}")
	public OrganizationsResponse getById(@PathVariable("id") Long id) {
		return organizationService.getById(id);
	}

	@GetMapping("{id}/followers/page")
	public Page<FollowerResponse> getFollowersById(@Valid PageableRequest pageableRequest,
			@PathVariable("id") Long id) {
		return organizationService.getFollowersByOrganizationId(id, pageableRequest);
	}

	@GetMapping("{id}/activities/list")
	public List<ActivityResponse> getActivitiesById(@PathVariable("id") Long id) {
		return organizationService.getOrganizationActivitiesById(id);
	}

	@GetMapping("{id}/deals")
	public List<DealsResponse> getDealsById(@PathVariable("id") Long id) {
		return organizationService.getDealsByOrganizationId(id);
	}

	@PutMapping("{id}")
	public Organizations update(@RequestBody OrganizationsRequest requestModel, @PathVariable("id") Long id) {
		return organizationService.update(requestModel, id);
	}

	@PutMapping("/{id}/address")
	public Organizations updateAddress(@RequestBody UpdateAddressRequest requestModel, @PathVariable("id") Long id) {
		return organizationService.updateAddress(requestModel, id);
	}

	@PutMapping("/{id}/details")
	public Organizations updateDetails(@RequestBody UpdateDetailsRequest requestModel, @PathVariable("id") Long id) {
		return organizationService.updateDetails(requestModel, id);
	}

	@PutMapping("/{id}/followers")
	public Organizations updateAddress(@RequestBody UpdateFollowerRequest requestModel, @PathVariable("id") Long id) {
		return organizationService.updateFollowers(requestModel, id);
	}

	@PutMapping("/{id}/persons")
	public Organizations updateAddress(@RequestBody UpdatePersonOrganizationRequestModel requestModel,
			@PathVariable("id") Long id) {
		return organizationService.updatePersons(requestModel, id);
	}

	@DeleteMapping("/{id}")
	public Long delete(@PathVariable("id") Long id) {
		return organizationService.delete(id);
	}

	@DeleteMapping("/{id}/tag")
	public Long deleteTag(@RequestBody TagRequest requestModel, @PathVariable("id") Long id) {
		return organizationService.deleteTag(requestModel, id);
	}

	@PostMapping("add")
	public Organizations add(@RequestBody OrganizationsRequest organizationRequest) {
		return organizationService.add(organizationRequest);
	}

	@PostMapping("/{id}/tag")
	public Organizations addTag(@RequestBody TagRequest requestModel, @PathVariable("id") Long id) {
		return organizationService.addTag(requestModel, id);
	}
}
