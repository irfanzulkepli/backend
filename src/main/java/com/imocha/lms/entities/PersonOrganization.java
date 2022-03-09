// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.PersonOrganizationIKey;

@Entity(name = "person_organization")
@IdClass(PersonOrganizationIKey.class)
public class PersonOrganization implements Serializable {

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

	/**
	 * Access method for jobTitle.
	 *
	 * @return the current value of jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Setter method for jobTitle.
	 *
	 * @param aJobTitle the new value for jobTitle
	 */
	public void setJobTitle(String aJobTitle) {
		jobTitle = aJobTitle;
	}

	/**
	 * Access method for organizations.
	 *
	 * @return the current value of organizations
	 */
	public Organizations getOrganizations() {
		return organizations;
	}

	/**
	 * Setter method for organizations.
	 *
	 * @param aOrganizations the new value for organizations
	 */
	public void setOrganizations(Organizations aOrganizations) {
		organizations = aOrganizations;
	}

	/**
	 * Access method for people.
	 *
	 * @return the current value of people
	 */
	public People getPeople() {
		return people;
	}

	/**
	 * Setter method for people.
	 *
	 * @param aPeople the new value for people
	 */
	public void setPeople(People aPeople) {
		people = aPeople;
	}

}
