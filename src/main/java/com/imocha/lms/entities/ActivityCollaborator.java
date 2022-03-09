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

import com.imocha.lms.users.entities.Users;

@Entity(name = "activity_collaborator")
@IdClass(ActivityCollaborator.ActivityCollaboratorId.class)
public class ActivityCollaborator implements Serializable {

	/**
	 * IdClass for primary key when using JPA annotations
	 */
	public static class ActivityCollaboratorId implements Serializable {
		Activities activities;
		Users users;
	}

	/** Primary key. */
	protected static final String PK = "ActivityCollaboratorPrimary";

	@ManyToOne(optional = false)
	@Id
	@JoinColumn(name = "activity_id", nullable = false)
	private Activities activities;
	@ManyToOne(optional = false)
	@Id
	@JoinColumn(name = "user_id", nullable = false)
	private Users users;

	/** Default constructor. */
	public ActivityCollaborator() {
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
	 * Access method for users.
	 *
	 * @return the current value of users
	 */
	public Users getUsers() {
		return users;
	}

	/**
	 * Setter method for users.
	 *
	 * @param aUsers the new value for users
	 */
	public void setUsers(Users aUsers) {
		users = aUsers;
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

	/** Temporary value holder for group key fragment usersId */
	private transient long tempUsersId;

	/**
	 * Gets the key fragment id for member users. If this.users is null, a temporary
	 * stored value for the key fragment will be returned. The temporary value is
	 * set by setUsersId. This behavior is required by some persistence libraries to
	 * allow fetching of objects in arbitrary order.
	 *
	 * @return Current (or temporary) value of the key fragment
	 * @see Users
	 */
	public long getUsersId() {
		return (getUsers() == null ? tempUsersId : getUsers().getId());
	}

	/**
	 * Sets the key fragment id from member users. If this.users is null, the passed
	 * value will be temporary stored, and returned by subsequent calls to
	 * getUsersId. This behaviour is required by some persistence libraries to allow
	 * fetching of objects in arbitrary order.
	 *
	 * @param aId New value for the key fragment
	 * @see Users
	 */
	public void setUsersId(long aId) {
		if (getUsers() == null) {
			tempUsersId = aId;
		} else {
			getUsers().setId(aId);
		}
	}

	/**
	 * Compares the key for this instance with another ActivityCollaborator.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class ActivityCollaborator and
	 *         the key objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActivityCollaborator)) {
			return false;
		}
		ActivityCollaborator that = (ActivityCollaborator) other;
		if (this.getActivitiesId() != that.getActivitiesId()) {
			return false;
		}
		if (this.getUsersId() != that.getUsersId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another ActivityCollaborator.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ActivityCollaborator))
			return false;
		return this.equalKeys(other) && ((ActivityCollaborator) other).equalKeys(this);
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
		i = (int) (getUsersId() ^ (getUsersId() >>> 32));
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
		StringBuffer sb = new StringBuffer("[ActivityCollaborator |");
		sb.append(" activitiesId=").append(getActivitiesId());
		sb.append(" usersId=").append(getUsersId());
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
		ret.put("usersId", Long.valueOf(getUsersId()));
		return ret;
	}

}
