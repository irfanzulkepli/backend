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
import org.springframework.stereotype.Service;

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

	public List<ContactTypes> list() {
		return contactTypesRepositories.findAll();
	}
	
	public ContactTypes update(long id, UpdateContactTypesRequest request) {
		Optional<ContactTypes> contactTypes = contactTypesRepositories.findById(id);
		ContactTypes updatedContactTypes = contactTypes.get();
		updatedContactTypes.setName(request.getName());
		updatedContactTypes.setClazz(request.getClazz());
		contactTypesRepositories.save(updatedContactTypes);
		return updatedContactTypes;
	}

	public ContactTypes get(long id) {
        Optional<ContactTypes> contactTypesOpt = contactTypesRepositories.findById(id);
        if (!contactTypesOpt.isPresent()) {
            // TODO: throw error
        }

        return contactTypesOpt.get();
    }

	public long delete(long id) {
        this.get(id);
        contactTypesRepositories.deleteById(id);
        return id;
    }

	public long addContactTypes(AddContactTypeRequest request) {

		ContactTypes contactTypes = new ContactTypes();
		contactTypes.setName(request.getName());
		contactTypes.setClazz(request.getClazz());

		ContactTypes savedContactTypes = contactTypesRepositories.save(contactTypes);
		return savedContactTypes.getId();
	}

	public List<ContactTypes> searchByName(String name) {		
		return contactTypesRepositories.findByName(name);
	}

}
