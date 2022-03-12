package com.imocha.lms.leads.model;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class OrganizationsRequest {

	private String name;
	private Long contactTypesId;
	private Long ownerId;
	private Long createdById;

	@Nullable
	private String address;

	@Nullable
	private String area;

	@Nullable
	private String city;

	@Nullable
	private String state;

	@Nullable
	private String zipCode;

	@Nullable
	private Long countryId;
}
