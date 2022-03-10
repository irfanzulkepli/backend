package com.imocha.lms.leads.model;

import java.util.List;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class PeopleRequest {

	private String name;

	@Nullable
	private Long contactTypesId;

	private List<PersonOrganizationRequest> personOrganizationRequests;
	private List<ContactRequest> phones;
	private List<ContactRequest> emails;

	private Long ownerId;

	private Long createdBy;

	@Nullable
	private String address;

	@Nullable
	private String zipCode;

	@Nullable
	private String city;

	@Nullable
	private String state;

	@Nullable
	private String area;

	@Nullable
	private Long countryId;

}
