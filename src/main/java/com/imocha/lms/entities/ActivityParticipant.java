// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.lms.leads.entities.People;

import lombok.Data;

@Entity(name = "activity_participant")
@IdClass(ActivityParticipant.ActivityParticipantId.class)
public class ActivityParticipant implements Serializable {

	/**
	 * IdClass for primary key when using JPA annotations
	 */
	@Data
	public static class ActivityParticipantId implements Serializable {
		Activities activities;
		People people;
	}

	/** Primary key. */
	protected static final String PK = "ActivityParticipantPrimary";

	@ManyToOne(optional = false)
	@Id
	@JoinColumn(name = "activity_id", nullable = false)
	private Activities activities;
	@ManyToOne(optional = false)
	@Id
	@JoinColumn(name = "person_id", nullable = false)
	private People people;

	/** Default constructor. */
	public ActivityParticipant() {
		super();
	}

	/**
	 * Access method for activities.
	 *
	 * @return the current value of activities
	 */
	public Activities getActivities() {
		return activities;
	}

	/**
	 * Setter method for activities.
	 *
	 * @param aActivities the new value for activities
	 */
	public void setActivities(Activities aActivities) {
		activities = aActivities;
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

	/** Temporary value holder for group key fragment activitiesId */
	private transient long tempActivitiesId;

	/**
	 * Gets the key fragment id for member activities. If this.activities is null, a
	 * temporary stored value for the key fragment will be returned. The temporary
	 * value is set by setActivitiesId. This behavior is required by some
	 * persistence libraries to allow fetching of objects in arbitrary order.
	 *
	 * @return Current (or temporary) value of the key fragment
	 * @see Activities
	 */
	public long getActivitiesId() {
		return (getActivities() == null ? tempActivitiesId : getActivities().getId());
	}

	/**
	 * Sets the key fragment id from member activities. If this.activities is null,
	 * the passed value will be temporary stored, and returned by subsequent calls
	 * to getActivitiesId. This behaviour is required by some persistence libraries
	 * to allow fetching of objects in arbitrary order.
	 *
	 * @param aId New value for the key fragment
	 * @see Activities
	 */
	public void setActivitiesId(long aId) {
		if (getActivities() == null) {
			tempActivitiesId = aId;
		} else {
			getActivities().setId(aId);
		}
	}

	/** Temporary value holder for group key fragment peopleId */
	private transient long tempPeopleId;

	/**
	 * Gets the key fragment id for member people. If this.people is null, a
	 * temporary stored value for the key fragment will be returned. The temporary
	 * value is set by setPeopleId. This behavior is required by some persistence
	 * libraries to allow fetching of objects in arbitrary order.
	 *
	 * @return Current (or temporary) value of the key fragment
	 * @see People
	 */
	public long getPeopleId() {
		return (getPeople() == null ? tempPeopleId : getPeople().getId());
	}

	/**
	 * Sets the key fragment id from member people. If this.people is null, the
	 * passed value will be temporary stored, and returned by subsequent calls to
	 * getPeopleId. This behaviour is required by some persistence libraries to
	 * allow fetching of objects in arbitrary order.
	 *
	 * @param aId New value for the key fragment
	 * @see People
	 */
	public void setPeopleId(long aId) {
		if (getPeople() == null) {
			tempPeopleId = aId;
		} else {
			getPeople().setId(aId);
		}
	}

	/**
	 * Compares the key for this instance with another ActivityParticipant.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class ActivityParticipant and the
	 *         key objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActivityParticipant)) {
			return false;
		}
		ActivityParticipant that = (ActivityParticipant) other;
		if (this.getActivitiesId() != that.getActivitiesId()) {
			return false;
		}
		if (this.getPeopleId() != that.getPeopleId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another ActivityParticipant.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ActivityParticipant))
			return false;
		return this.equalKeys(other) && ((ActivityParticipant) other).equalKeys(this);
	}

	/**
	 * Returns a hash code for this instance.
	 *
	 * @return Hash code
	 */
	@Override
	public int hashCode() {
		int i;
		int result = 17;
		i = (int) (getActivitiesId() ^ (getActivitiesId() >>> 32));
		result = 37 * result + i;
		i = (int) (getPeopleId() ^ (getPeopleId() >>> 32));
		result = 37 * result + i;
		return result;
	}

	/**
	 * Returns a debug-friendly String representation of this instance.
	 *
	 * @return String representation of this instance
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[ActivityParticipant |");
		sb.append(" activitiesId=").append(getActivitiesId());
		sb.append(" peopleId=").append(getPeopleId());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Return all elements of the primary key.
	 *
	 * @return Map of key names to values
	 */
	public Map<String, Object> getPrimaryKey() {
		Map<String, Object> ret = new LinkedHashMap<String, Object>(6);
		ret.put("activitiesId", Long.valueOf(getActivitiesId()));
		ret.put("peopleId", Long.valueOf(getPeopleId()));
		return ret;
	}

}
