// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.imocha.lms.leads.entities.People;

@Entity(name = "person_organization")
public class PersonOrganization implements Serializable {

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    @Version
    @Column(name = "LOCK_FLAG")
    private Integer lockFlag;

    /**
     * Access method for the lockFlag property.
     *
     * @return the current value of the lockFlag property
     */
    public Integer getLockFlag() {
        return lockFlag;
    }

    /**
     * Sets the value of the lockFlag property.
     *
     * @param aLockFlag the new value of the lockFlag property
     */
    public void setLockFlag(Integer aLockFlag) {
        lockFlag = aLockFlag;
    }

    @Column(name = "job_title", length = 191)
    private String jobTitle;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organizations organizations;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private People people;

    /** Default constructor. */
    public PersonOrganization() {
        super();
    }

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
