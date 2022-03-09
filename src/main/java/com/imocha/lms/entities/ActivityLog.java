// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "activity_log", indexes = { @Index(name = "activityLogCauser", columnList = "causer_id,causer_type"),
		@Index(name = "activityLogSubject", columnList = "subject_id,subject_type") })
public class ActivityLog implements Serializable {

	/** Primary key. */
	protected static final String PK = "id";

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
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;

	/** Default constructor. */
	public ActivityLog() {
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
	 * Access method for logName.
	 *
	 * @return the current value of logName
	 */
	public String getLogName() {
		return logName;
	}

	/**
	 * Setter method for logName.
	 *
	 * @param aLogName the new value for logName
	 */
	public void setLogName(String aLogName) {
		logName = aLogName;
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
	 * Access method for subjectId.
	 *
	 * @return the current value of subjectId
	 */
	public long getSubjectId() {
		return subjectId;
	}

	/**
	 * Setter method for subjectId.
	 *
	 * @param aSubjectId the new value for subjectId
	 */
	public void setSubjectId(long aSubjectId) {
		subjectId = aSubjectId;
	}

	/**
	 * Access method for subjectType.
	 *
	 * @return the current value of subjectType
	 */
	public String getSubjectType() {
		return subjectType;
	}

	/**
	 * Setter method for subjectType.
	 *
	 * @param aSubjectType the new value for subjectType
	 */
	public void setSubjectType(String aSubjectType) {
		subjectType = aSubjectType;
	}

	/**
	 * Access method for causerId.
	 *
	 * @return the current value of causerId
	 */
	public long getCauserId() {
		return causerId;
	}

	/**
	 * Setter method for causerId.
	 *
	 * @param aCauserId the new value for causerId
	 */
	public void setCauserId(long aCauserId) {
		causerId = aCauserId;
	}

	/**
	 * Access method for causerType.
	 *
	 * @return the current value of causerType
	 */
	public String getCauserType() {
		return causerType;
	}

	/**
	 * Setter method for causerType.
	 *
	 * @param aCauserType the new value for causerType
	 */
	public void setCauserType(String aCauserType) {
		causerType = aCauserType;
	}

	/**
	 * Access method for properties.
	 *
	 * @return the current value of properties
	 */
	public String getProperties() {
		return properties;
	}

	/**
	 * Setter method for properties.
	 *
	 * @param aProperties the new value for properties
	 */
	public void setProperties(String aProperties) {
		properties = aProperties;
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
	 * Compares the key for this instance with another ActivityLog.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class ActivityLog and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActivityLog)) {
			return false;
		}
		ActivityLog that = (ActivityLog) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another ActivityLog.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ActivityLog))
			return false;
		return this.equalKeys(other) && ((ActivityLog) other).equalKeys(this);
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
		StringBuffer sb = new StringBuffer("[ActivityLog |");
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
