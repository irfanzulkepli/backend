package com.imocha.lms.leads.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.common.model.EmailResponse;
import com.imocha.lms.common.model.PhoneResponse;
import com.imocha.lms.common.service.EmailService;
import com.imocha.lms.common.service.PhoneService;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.PersonListResponse;
import com.imocha.lms.leads.model.PersonPageResponse;
import com.imocha.lms.leads.repositories.PeopleRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PhoneService phoneService;

    public Page<PersonPageResponse> page(PageableRequest pageableRequest) {
        int page = pageableRequest.getPage();
        int size = pageableRequest.getSize();
        Direction direction = pageableRequest.getDirection();
        String[] properties = pageableRequest.getProperties();

        PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
        Page<People> peoplePage = this.peopleRepository.findAll(pageRequest);

        List<PersonPageResponse> personResponseList = peoplePage.getContent().stream().map(people -> {
            Long id = people.getId();

            PersonPageResponse personResponse = new PersonPageResponse();

            ContactTypesResponse contactTypes = new ContactTypesResponse();
            BeanUtils.copyProperties(people.getContactTypes(), contactTypes);

            BeanUtils.copyProperties(people, personResponse);
            personResponse.setContactTypes(contactTypes);

            List<EmailResponse> emails = emailService.getPersonEmailById(id);
            personResponse.setEmails(emails);

            List<PhoneResponse> phones = phoneService.getPersonPhoneById(id);
            personResponse.setPhones(phones);

            OwnerResponse owner = new OwnerResponse();
            BeanUtils.copyProperties(people.getOwner(), owner);

            personResponse.setOwner(owner);

            return personResponse;
        }).collect(Collectors.toList());

        Page<PersonPageResponse> personResponsePageImpl = new PageImpl<>(personResponseList, pageRequest,
                peoplePage.getTotalElements());
        return personResponsePageImpl;
    }

    public List<PersonListResponse> list() {
        List<People> list = peopleRepository.findAll();
        List<PersonListResponse> response = new ArrayList<PersonListResponse>();
        for (People people : list) {
            PersonListResponse personListResponse = this.mapPeopleToPersonListResponse(people);
            response.add(personListResponse);
        }
        return response;
    }

    private PersonListResponse mapPeopleToPersonListResponse(People people) {
        PersonListResponse response = new PersonListResponse();
        BeanUtils.copyProperties(people, response);
        return response;
    }
}
