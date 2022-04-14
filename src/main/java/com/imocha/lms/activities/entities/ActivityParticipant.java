// Generated with g9.

package com.imocha.lms.activities.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.common.audit.Auditable;
import com.imocha.lms.leads.entities.People;

import lombok.Data;

@Data
@Entity(name = "activity_participant")
@IdClass(ActivityParticipant.ActivityParticipantId.class)
public class ActivityParticipant extends Auditable<String> {

	/**
	 * IdClass for primary key when using JPA annotations
	 */
	@Data
	public static class ActivityParticipantId implements Serializable {
		Long activities;
		Long people;
	}

	@ManyToOne(optional = false)
	@Id
	@JoinColumn(name = "activity_id", nullable = false)
	private Activities activities;
	@ManyToOne(optional = false)
	@Id
	@JoinColumn(name = "person_id", nullable = false)
	private People people;

}
