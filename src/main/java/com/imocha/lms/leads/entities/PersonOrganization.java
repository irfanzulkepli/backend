// Generated with g9.

package com.imocha.lms.leads.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.common.audit.Auditable;
import com.imocha.lms.leads.model.PersonOrganizationIKey;

import lombok.Data;

@Data
@Entity(name = "person_organization")
@IdClass(PersonOrganizationIKey.class)
public class PersonOrganization extends Auditable<String> {

	@Column(name = "job_title", length = 191)
	private String jobTitle;

	@Id
	@ManyToOne
	@JoinColumn(name = "organization_id")
	private Organizations organizations;

	@Id
	@ManyToOne
	@JoinColumn(name = "person_id")
	private People people;

}
