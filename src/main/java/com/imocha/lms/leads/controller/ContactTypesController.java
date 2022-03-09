package com.imocha.lms.leads.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.leads.entities.ContactTypes;
import com.imocha.lms.leads.model.UpdateContactTypesRequest;
import com.imocha.lms.leads.repositories.ContactTypesRepositories;
import com.imocha.lms.leads.service.ContactTypesService;

@RestController
@RequestMapping("lms/leads/contactTypes")
public class ContactTypesController {

	@Autowired
	private ContactTypesService contactTpesService;

	@Autowired
	private ContactTypesRepositories repository;

	/**
	 * Get all users list.
	 *
	 * @return the list
	 */
	@GetMapping("/list")
	public Page<ContactTypesResponse> list(@Valid PageableRequest pageableRequest) {
		return contactTpesService.list(pageableRequest);
	}

	@PutMapping()
	public ContactTypes putName(@RequestBody UpdateContactTypesRequest request) {
		return contactTpesService.putName(request);
	}
}
