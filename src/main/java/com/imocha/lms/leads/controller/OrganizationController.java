package com.imocha.lms.leads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imocha.lms.leads.repositories.OrganizationRepository;
import com.imocha.lms.leads.service.OrganizationService;

@RestController
@RequestMapping("organization")
public class OrganizationController {

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	OrganizationService organizationService;

//	@GetMapping("list")
//	public Organizations listOrganization() {
//		return organizationService.listOrganization();
//	}

}
