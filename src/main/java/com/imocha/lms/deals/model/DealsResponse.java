package com.imocha.lms.deals.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.common.model.StatusesResponse;
import com.imocha.lms.common.model.TagResponse;
import com.imocha.lms.deals.pipelines.model.PipelinesResponse;
import com.imocha.lms.deals.pipelines.model.StagesResponse;
import com.imocha.lms.leads.model.OrganizationsResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.PeopleResponse;
import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
public class DealsResponse {
	private long id;
	private String title;
	private long value;
	private ContextableTypes contextableType;
	private long contextableId;

	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expiredAt;

	private String comment;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatedAt;

	private Users createdBy;
	private LostReasonsResponse lostReasons;
	private OwnerResponse owner;
	private PipelinesResponse pipelines;
	private StagesResponse stages;
	private StatusesResponse statuses;
	private PeopleResponse person;
	private OrganizationsResponse organization;

	private List<TagResponse> tags;
}
