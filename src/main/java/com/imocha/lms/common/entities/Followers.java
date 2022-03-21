// Generated with g9.

package com.imocha.lms.common.entities;

import java.io.Serializable;
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

import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.leads.entities.People;

import lombok.Data;

@Data
@Entity
@Table(name = "followers", indexes = {
		@Index(name = "followersFollowersContextableTypeContextableIdIndex", columnList = "contextable_type,contextable_id") })
public class Followers implements Serializable {

	/** Primary key. */
	protected static final String PK = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 20)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "contextable_type", nullable = false, length = 191)
	private ContextableTypes contextableType;
	@Column(name = "contextable_id", nullable = false, precision = 20)
	private long contextableId;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@ManyToOne(optional = false)
	@JoinColumn(name = "person_id", nullable = false)
	private People people;

}
