package com.imocha.lms.leads.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imocha.lms.common.entities.Countries;
import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.common.model.TagResponse;

import lombok.Data;

@Data
public class OrganizationsResponse {

	private Long id;
	private String name;
	private String address;
	private String area;
	private String city;
	private String state;
	private String zipCode;
	private Countries country;
	private ContactTypesResponse contactTypes;
	private OwnerResponse owner;
	private List<TagResponse> tags;
	private List<PersonsResponse> persons;
	private Long openDealsCount;
	private Long closedDealsCount;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;
}
