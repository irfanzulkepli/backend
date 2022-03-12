package com.imocha.lms.leads.model;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class UpdatePeopleRequest {

	private String name;

	@Nullable
	private Long contactTypesId;

	private UpdateContactRequestModel emails;
	private UpdateContactRequestModel phones;
	private UpdatePersonOrganizationRequestModel personOrganizations;

	private Long ownerId;

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
