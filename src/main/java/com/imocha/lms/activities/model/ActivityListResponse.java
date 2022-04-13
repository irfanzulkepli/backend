package com.imocha.lms.activities.model;

import java.util.List;

import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.leads.model.ActivityTypeResponse;
import com.imocha.lms.leads.model.CollaboratorResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.ParticipantResponse;

import lombok.Data;

@Data
public class ActivityListResponse {

	private Long id;
	private String title;
	private String description;

	private Statuses status;
	private ActivityTypeResponse activityType;
	private OwnerResponse createdBy;
	private List<ParticipantResponse> participants;
	private List<CollaboratorResponse> collaborators;

	private String startedAt;
	private String endedAt;
	private String startTime;
	private String endTime;

}
