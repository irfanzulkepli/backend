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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.imocha.lms.leads.entities.People;

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
	@Column(name = "contextable_type", nullable = false, length = 191)
	private String contextableType;
	@Column(name = "contextable_id", nullable = false, precision = 20)
	private long contextableId;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@ManyToOne(optional = false)
	@JoinColumn(name = "person_id", nullable = false)
	private People people;

	/** Default constructor. */
	public Followers() {
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

	/**
	 * Compares the key for this instance with another Followers.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class Followers and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Followers)) {
			return false;
		}
		Followers that = (Followers) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another Followers.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Followers))
			return false;
		return this.equalKeys(other) && ((Followers) other).equalKeys(this);
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
		StringBuffer sb = new StringBuffer("[Followers |");
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
