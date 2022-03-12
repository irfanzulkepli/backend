package com.imocha.lms.leads.model;

import com.imocha.lms.common.model.ContactTypesResponse;

import lombok.Data;

@Data
public class PersonsResponse {

	private Long id;
	private String name;
	private String jobTitle;
	private ContactTypesResponse contactTypes;
}
