package com.imocha.lms.leads.model;

import java.util.List;

import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.common.model.TagResponse;

import lombok.Data;

@Data
public class FollowerResponse {

	private Long id;
	private String name;
	private Long openDealsCount;
	private Long closedDealsCount;
	private OwnerResponse owner;
	private List<TagResponse> tags;
	private ContactTypesResponse contactTypes;
}
