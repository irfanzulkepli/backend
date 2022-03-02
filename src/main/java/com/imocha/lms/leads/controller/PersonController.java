package com.imocha.lms.leads.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.leads.model.PersonResponse;
import com.imocha.lms.leads.service.PeopleService;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping("list")
    public Page<PersonResponse> list(@Valid PageableRequest pageableRequest) {
        return peopleService.list(pageableRequest);
    }
}
