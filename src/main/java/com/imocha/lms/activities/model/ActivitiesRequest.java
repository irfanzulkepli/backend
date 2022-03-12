package com.imocha.lms.activities.model;

import java.util.Date;
import java.util.List;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class ActivitiesRequest {

	private Long activityTypeId;
	private String title;
	private String description;
	private Date startedAt;
	private Date endedAt;
	private Date startTime;
	private Date endTime;
	private List<Long> collaboratorsIds;
	private List<Long> participantsIds;
	private Long statusId;
	private String contextableType;
	private Long contextableId;

	@Nullable
	private Long createdById;
}
