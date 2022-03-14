package com.imocha.lms.deals.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.deals.pipelines.entities.Pipelines;
import com.imocha.lms.deals.pipelines.entities.Stages;
import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
@Entity(name = "deals")
public class Deals implements Serializable {

	/** Primary key. */
	protected static final String PK = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 20)
	private Long id;
	@Column(nullable = false, length = 191)
	private String title;
	@Column(nullable = false, precision = 19)
	private long value;
	@Column(name = "contextable_type", nullable = false, length = 191)
	private String contextableType;
	@Column(name = "contextable_id", nullable = false, precision = 19)
	private long contextableId;

	private String description;
	@Column(name = "expired_at")
	private Date expiredAt;
	private String comment;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	private String histories;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private Users createdBy;
	@ManyToOne
	@JoinColumn(name = "lost_reason_id")
	private LostReasons lostReasons;
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Users owner;
	@ManyToOne(optional = false)
	@JoinColumn(name = "pipeline_id", nullable = false)
	private Pipelines pipelines;
	@ManyToOne(optional = false)
	@JoinColumn(name = "stage_id", nullable = false)
	private Stages stages;
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Statuses statuses;
}
