package com.imocha.lms.leads.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import net.bytebuddy.implementation.bytecode.Throw;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.leads.entities.ContactTypes;
import com.imocha.lms.leads.model.AddContactTypeRequest;
import com.imocha.lms.leads.model.UpdateContactTypesRequest;
import com.imocha.lms.leads.repositories.ContactTypesRepositories;

@Service
public class ContactTypesService {

	@Autowired
	private ContactTypesRepositories contactTypesRepositories;

	public Page<ContactTypesResponse> page(PageableRequest pageableRequest) {
		int page = pageableRequest.getPage();
		int size = pageableRequest.getSize();
		Direction direction = pageableRequest.getDirection();
		String[] properties = pageableRequest.getProperties();

		PageRequest pageRequest = PageRequest.of(page, size, direction, properties);

		Page<ContactTypes> contactTypePage = contactTypesRepositories.findAll(pageRequest);

		List<ContactTypesResponse> contactTypeResponseList = contactTypePage.getContent().stream().map(contactType -> {

			ContactTypesResponse contactTypesResponse = new ContactTypesResponse();
			BeanUtils.copyProperties(contactType, contactTypesResponse);

			return contactTypesResponse;
		}).collect(Collectors.toList());

		Page<ContactTypesResponse> contactTypesResponsePageImpl = new PageImpl<>(contactTypeResponseList, pageRequest,
				contactTypePage.getTotalElements());
		return contactTypesResponsePageImpl;
	}

	public ContactTypes update(long id, UpdateContactTypesRequest request) {
		Optional<ContactTypes> contactTypes = contactTypesRepositories.findById(id);
		ContactTypes updatedContactTypes = contactTypes.get();
		updatedContactTypes.setName(request.getName());
		updatedContactTypes.setClazz(request.getClazz());
		contactTypesRepositories.save(updatedContactTypes);
		return updatedContactTypes;
	}

	public ContactTypes findById(long id) {
		Optional<ContactTypes> contactTypesOpt = contactTypesRepositories.findById(id);
		ContactTypes contactTypes = contactTypesOpt.orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

		if (!contactTypes.isActive()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity has been deleted");
		}

		return contactTypes;
	}

	public long delete(long id) {
		Optional<ContactTypes> contactTypesOpt = contactTypesRepositories.findById(id);
		ContactTypes contactTypes = contactTypesOpt.orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

		contactTypes.setActive(false);
		contactTypesRepositories.save(contactTypes);

		return contactTypes.getId();
	}

	public long addContactTypes(AddContactTypeRequest request) {

		ContactTypes contactTypes = new ContactTypes();
		contactTypes.setName(request.getName());
		contactTypes.setClazz(request.getClazz());

		ContactTypes savedContactTypes = contactTypesRepositories.save(contactTypes);
		return savedContactTypes.getId();
	}

	public List<ContactTypes> searchByName(String name) {		
		return contactTypesRepositories.findByNameContaining(name);
	}

}
