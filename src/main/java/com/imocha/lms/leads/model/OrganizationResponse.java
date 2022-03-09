package com.imocha.lms.leads.model;

import com.imocha.lms.common.model.ContactTypesResponse;

import lombok.Data;

@Data
public class OrganizationResponse {

	private Long id;
	private String name;
	private String jobTitle;
	private ContactTypesResponse contactTypes;
}
