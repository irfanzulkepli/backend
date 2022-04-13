package com.imocha.lms.activities.model;

import java.util.List;

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
public class ActivityResponse {

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

	private String startedAt;
	private String endedAt;
	private String startTime;
	private String endTime;
    
}
