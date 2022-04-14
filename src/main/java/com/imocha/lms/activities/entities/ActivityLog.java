// Generated with g9.

package com.imocha.lms.activities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.imocha.common.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "activity_log", indexes = { @Index(name = "activityLogCauser", columnList = "causer_id,causer_type"),
		@Index(name = "activityLogSubject", columnList = "subject_id,subject_type") })
public class ActivityLog extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 20)
	private long id;
	@Column(name = "log_name", length = 160)
	private String logName;
	@Column(nullable = false)
	private String description;
	@Column(name = "subject_id", precision = 20)
	private long subjectId;
	@Column(name = "subject_type", length = 160)
	private String subjectType;
	@Column(name = "causer_id", precision = 20)
	private long causerId;
	@Column(name = "causer_type", length = 160)
	private String causerType;
	private String properties;
}
