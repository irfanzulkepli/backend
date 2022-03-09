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
import com.imocha.lms.leads.model.UpdateContactTypesRequest;
import com.imocha.lms.leads.repositories.ContactTypesRepositories;

@Service
public class ContactTypesService {
	
	@Autowired
	private ContactTypesRepositories contactTypesRepositories;
		
	public Page<ContactTypesResponse> list(PageableRequest pageableRequest) {
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
	
	public ContactTypes putName(UpdateContactTypesRequest request) {
		Optional<ContactTypes> contactTypes = contactTypesRepositories.findById(request.getId());
		ContactTypes updatedContactTypes = contactTypes.get();
		updatedContactTypes.setName(request.getName());
		contactTypesRepositories.save(updatedContactTypes);
		return updatedContactTypes;
	}

}
