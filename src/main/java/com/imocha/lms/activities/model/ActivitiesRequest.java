package com.imocha.lms.activities.model;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imocha.lms.common.enumerator.ContextableTypes;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class ActivitiesRequest {

	private Long activityTypeId;
	private String title;
	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startedAt;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endedAt;

	@JsonFormat(pattern = "HH:mm")
	private Date startTime;
	@JsonFormat(pattern = "HH:mm")
	private Date endTime;

	private Set<Long> collaboratorsIds;
	private Set<Long> participantsIds;
	private boolean markAsDone;
	private ContextableTypes contextableType;
	private Long dealsId;
	private Long personsId;
	private Long organizationsId;

	@Nullable
	private Long createdById;
}
