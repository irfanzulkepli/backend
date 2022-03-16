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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.leads.entities.ContactTypes;
import com.imocha.lms.leads.model.AddContactTypeRequest;
import com.imocha.lms.leads.model.UpdateContactTypesRequest;
import com.imocha.lms.leads.service.ContactTypesService;

@RestController
@RequestMapping("lms/leads/contactTypes")
public class ContactTypesController {

	@Autowired
	private ContactTypesService contactTypesService;

	@GetMapping("/page")
	public Page<ContactTypesResponse> page(@Valid PageableRequest pageableRequest) {
		return contactTypesService.page(pageableRequest);
	}

	@GetMapping("/name")
	public List<ContactTypes> searchByName(@RequestParam(required = false) String name) {
		return contactTypesService.searchByName(name);
	}

	@PutMapping("/{id}")
	public ContactTypes update(@RequestBody UpdateContactTypesRequest request, @PathVariable long id) {
		return contactTypesService.update(id,request);
	}

	@PostMapping()
	public long addContactTypes(@RequestBody AddContactTypeRequest request) {
		return contactTypesService.addContactTypes(request);
	}

	@DeleteMapping("/{id}")
    public long delete(@PathVariable long id) {
        return contactTypesService.delete(id);
    }
}
