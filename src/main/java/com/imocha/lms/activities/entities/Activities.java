// Generated with g9.

package com.imocha.lms.activities.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.users.entities.Users;

@Entity
@Table(name = "activities", indexes = {
		@Index(name = "activitiesActivitiesContextableTypeContextableIdIndex", columnList = "contextable_type,contextable_id") })
public class Activities implements Serializable {

	/** Primary key. */
	protected static final String PK = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 20)
	private long id;
	private String title;
	private String description;
	@Column(name = "contextable_type", length = 191)
	private String contextableType;
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
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@ManyToOne
	@JoinColumn(name = "activity_type_id")
	private ActivityTypes activityTypes;
	@ManyToOne
	@JoinColumn(name = "created_by")
	private Users users;
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Statuses statuses;
	@OneToMany(mappedBy = "activities")
	private Set<ActivityCollaborator> activityCollaborator;
	@OneToMany(mappedBy = "activities")
	private Set<ActivityParticipant> activityParticipant;

	/** Default constructor. */
	public Activities() {
		super();
	}

	/**
	 * Access method for id.
	 *
	 * @return the current value of id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter method for id.
	 *
	 * @param aId the new value for id
	 */
	public void setId(long aId) {
		id = aId;
	}

	/**
	 * Access method for title.
	 *
	 * @return the current value of title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter method for title.
	 *
	 * @param aTitle the new value for title
	 */
	public void setTitle(String aTitle) {
		title = aTitle;
	}

	/**
	 * Access method for description.
	 *
	 * @return the current value of description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter method for description.
	 *
	 * @param aDescription the new value for description
	 */
	public void setDescription(String aDescription) {
		description = aDescription;
	}

	/**
	 * Access method for contextableType.
	 *
	 * @return the current value of contextableType
	 */
	public String getContextableType() {
		return contextableType;
	}

	/**
	 * Setter method for contextableType.
	 *
	 * @param aContextableType the new value for contextableType
	 */
	public void setContextableType(String aContextableType) {
		contextableType = aContextableType;
	}

	/**
	 * Access method for contextableId.
	 *
	 * @return the current value of contextableId
	 */
	public long getContextableId() {
		return contextableId;
	}

	/**
	 * Setter method for contextableId.
	 *
	 * @param aContextableId the new value for contextableId
	 */
	public void setContextableId(long aContextableId) {
		contextableId = aContextableId;
	}

	/**
	 * Access method for startedAt.
	 *
	 * @return the current value of startedAt
	 */
	public Date getStartedAt() {
		return startedAt;
	}

	/**
	 * Setter method for startedAt.
	 *
	 * @param aStartedAt the new value for startedAt
	 */
	public void setStartedAt(Date aStartedAt) {
		startedAt = aStartedAt;
	}

	/**
	 * Access method for endedAt.
	 *
	 * @return the current value of endedAt
	 */
	public Date getEndedAt() {
		return endedAt;
	}

	/**
	 * Setter method for endedAt.
	 *
	 * @param aEndedAt the new value for endedAt
	 */
	public void setEndedAt(Date aEndedAt) {
		endedAt = aEndedAt;
	}

	/**
	 * Access method for startTime.
	 *
	 * @return the current value of startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Setter method for startTime.
	 *
	 * @param aStartTime the new value for startTime
	 */
	public void setStartTime(Date aStartTime) {
		startTime = aStartTime;
	}

	/**
	 * Access method for endTime.
	 *
	 * @return the current value of endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Setter method for endTime.
	 *
	 * @param aEndTime the new value for endTime
	 */
	public void setEndTime(Date aEndTime) {
		endTime = aEndTime;
	}

	/**
	 * Access method for createdAt.
	 *
	 * @return the current value of createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Setter method for createdAt.
	 *
	 * @param aCreatedAt the new value for createdAt
	 */
	public void setCreatedAt(Date aCreatedAt) {
		createdAt = aCreatedAt;
	}

	/**
	 * Access method for updatedAt.
	 *
	 * @return the current value of updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Setter method for updatedAt.
	 *
	 * @param aUpdatedAt the new value for updatedAt
	 */
	public void setUpdatedAt(Date aUpdatedAt) {
		updatedAt = aUpdatedAt;
	}

	/**
	 * Access method for activityTypes.
	 *
	 * @return the current value of activityTypes
	 */
	public ActivityTypes getActivityTypes() {
		return activityTypes;
	}

	/**
	 * Setter method for activityTypes.
	 *
	 * @param aActivityTypes the new value for activityTypes
	 */
	public void setActivityTypes(ActivityTypes aActivityTypes) {
		activityTypes = aActivityTypes;
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

	/**
	 * Access method for statuses.
	 *
	 * @return the current value of statuses
	 */
	public Statuses getStatuses() {
		return statuses;
	}

	/**
	 * Setter method for statuses.
	 *
	 * @param aStatuses the new value for statuses
	 */
	public void setStatuses(Statuses aStatuses) {
		statuses = aStatuses;
	}

	/**
	 * Access method for activityCollaborator.
	 *
	 * @return the current value of activityCollaborator
	 */
	public Set<ActivityCollaborator> getActivityCollaborator() {
		return activityCollaborator;
	}

	/**
	 * Setter method for activityCollaborator.
	 *
	 * @param aActivityCollaborator the new value for activityCollaborator
	 */
	public void setActivityCollaborator(Set<ActivityCollaborator> aActivityCollaborator) {
		activityCollaborator = aActivityCollaborator;
	}

	/**
	 * Access method for activityParticipant.
	 *
	 * @return the current value of activityParticipant
	 */
	public Set<ActivityParticipant> getActivityParticipant() {
		return activityParticipant;
	}

	/**
	 * Setter method for activityParticipant.
	 *
	 * @param aActivityParticipant the new value for activityParticipant
	 */
	public void setActivityParticipant(Set<ActivityParticipant> aActivityParticipant) {
		activityParticipant = aActivityParticipant;
	}

	/**
	 * Compares the key for this instance with another Activities.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Activities and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Activities)) {
			return false;
		}
		Activities that = (Activities) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Activities.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Activities))
			return false;
		return this.equalKeys(other) && ((Activities) other).equalKeys(this);
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
		i = (int) (getId() ^ (getId() >>> 32));
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
		StringBuffer sb = new StringBuffer("[Activities |");
		sb.append(" id=").append(getId());
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
		ret.put("id", Long.valueOf(getId()));
		return ret;
	}

}
