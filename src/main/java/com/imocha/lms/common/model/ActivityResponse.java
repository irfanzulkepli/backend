package com.imocha.lms.common.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imocha.lms.leads.model.ActivityTypeResponse;
import com.imocha.lms.leads.model.CollaboratorResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.leads.model.ParticipantResponse;

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

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	@JsonFormat(pattern = "HH:mm:ss")
	private Date startTime;

	@JsonFormat(pattern = "HH:mm:ss")
	private Date endTime;
}
