package com.imocha.lms.leads.controller;

import java.util.List;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.leads.model.PersonListResponse;
import com.imocha.lms.leads.model.PersonPageResponse;
import com.imocha.lms.leads.service.PeopleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping("page")
    public Page<PersonPageResponse> page(@Valid PageableRequest pageableRequest) {
        return peopleService.page(pageableRequest);
    }

    @GetMapping("list")
    public List<PersonListResponse> list(){
        return peopleService.list();
    }

}
