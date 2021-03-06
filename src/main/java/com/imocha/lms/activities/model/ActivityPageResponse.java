package com.imocha.lms.activities.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.deals.model.DealsResponse;
import com.imocha.lms.leads.model.ActivityTypeResponse;
import com.imocha.lms.leads.model.CollaboratorResponse;
import com.imocha.lms.leads.model.OrganizationResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.ParticipantResponse;
import com.imocha.lms.leads.model.PeopleResponse;

import lombok.Data;

@Data
public class ActivityPageResponse {

	private Long id;
	private String title;
	private String description;
	private List<ParticipantResponse> participants;
	private List<CollaboratorResponse> collaborators;
	private OwnerResponse createdBy;
	private ActivityTypeResponse activityType;
	private boolean markAsDone;
	private Statuses status;
	private ContextableTypes contextableType;
	private long contextableId;
	private PeopleResponse person;
	private OrganizationResponse organization;
	private DealsResponse deal;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startedAt;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endedAt;

	@JsonFormat(pattern = "HH:mm:ss")
	private Date startTime;

	@JsonFormat(pattern = "HH:mm:ss")
	private Date endTime;
}
