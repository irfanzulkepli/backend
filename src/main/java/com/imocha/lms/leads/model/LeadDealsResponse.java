package com.imocha.lms.leads.model;

import java.util.List;

import com.imocha.lms.common.model.StatusesResponse;
import com.imocha.lms.common.model.TagResponse;

import lombok.Data;

@Data
public class LeadDealsResponse {

	private Long id;
	private Long value;
	private String title;
	private OwnerResponse owner;
	private PeopleResponse person;
	private OrganizationResponse organization;
	private String contactPerson;
	private List<TagResponse> tags;
	private String nextActivity;
	private StatusesResponse status;
}
