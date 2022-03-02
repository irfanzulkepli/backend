package com.imocha.lms.leads.service;

import java.util.List;
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
import com.imocha.lms.common.model.EmailResponse;
import com.imocha.lms.common.model.PhoneResponse;
import com.imocha.lms.common.service.EmailService;
import com.imocha.lms.common.service.PhoneService;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.PersonResponse;
import com.imocha.lms.leads.repositories.PeopleRepository;

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

    public Page<PersonResponse> list(PageableRequest pageableRequest) {
        int page = pageableRequest.getPage();
        int size = pageableRequest.getSize();
        Direction direction = pageableRequest.getDirection();
        String[] properties = pageableRequest.getProperties();

        PageRequest pageRequest = PageRequest.of(page, size, direction, properties);
        Page<People> peoplePage = this.peopleRepository.findAll(pageRequest);

        List<PersonResponse> personResponseList = peoplePage.getContent().stream().map(people -> {
            Long id = people.getId();

            PersonResponse personResponse = new PersonResponse();

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

        Page<PersonResponse> personResponsePageImpl = new PageImpl<>(personResponseList, pageRequest,
                peoplePage.getTotalElements());
        return personResponsePageImpl;
    }

}
