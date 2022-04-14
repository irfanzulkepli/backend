// Generated with g9.

package com.imocha.lms.activities.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.imocha.common.audit.Auditable;
import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.common.enumerator.ContextableTypes;

import lombok.Data;

@Data
@Entity
@Table(name = "activities", indexes = {
		@Index(name = "activitiesActivitiesContextableTypeContextableIdIndex", columnList = "contextable_type,contextable_id") })
public class Activities extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 20)
	private long id;
	private String title;
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "contextable_type", length = 191)
	private ContextableTypes contextableType;
	@Column(name = "contextable_id", precision = 20)
	private long contextableId;
	@Column(name = "started_at")
	private Date startedAt;
	@Column(name = "ended_at")
	private Date endedAt;
	@Column(name = "start_time")
	private Date startTime;
	@Column(name = "end_time")
	private Date endTime;
	@ManyToOne
	@JoinColumn(name = "activity_type_id")
	private ActivityTypes activityTypes;
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Statuses statuses;

}
