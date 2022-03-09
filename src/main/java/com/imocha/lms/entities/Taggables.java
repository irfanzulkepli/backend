// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "taggables", indexes = {
		@Index(name = "taggablesTaggablesTagIdTaggableTypeTaggableIdUnique", columnList = "tag_id,taggable_type,taggable_id", unique = true),
		@Index(name = "taggablesTaggablesTaggableTypeTaggableIdIndex", columnList = "taggable_type,taggable_id") })
public class Taggables implements Serializable {

	@Column(name = "taggable_type", length = 191)
	private String taggableType;

	@Id
	@Column(name = "taggable_id", precision = 20)
	private long taggableId;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@ManyToOne(optional = false)
	@JoinColumn(name = "tag_id", nullable = false)
	private Tags tags;

	/** Default constructor. */
	public Taggables() {
		super();
	}

	/**
	 * Access method for taggableType.
	 *
	 * @return the current value of taggableType
	 */
	public String getTaggableType() {
		return taggableType;
	}

	/**
	 * Setter method for taggableType.
	 *
	 * @param aTaggableType the new value for taggableType
	 */
	public void setTaggableType(String aTaggableType) {
		taggableType = aTaggableType;
	}

	/**
	 * Access method for taggableId.
	 *
	 * @return the current value of taggableId
	 */
	public long getTaggableId() {
		return taggableId;
	}

	/**
	 * Setter method for taggableId.
	 *
	 * @param aTaggableId the new value for taggableId
	 */
	public void setTaggableId(long aTaggableId) {
		taggableId = aTaggableId;
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
	 * Access method for tags.
	 *
	 * @return the current value of tags
	 */
	public Tags getTags() {
		return tags;
	}

	/**
	 * Setter method for tags.
	 *
	 * @param aTags the new value for tags
	 */
	public void setTags(Tags aTags) {
		tags = aTags;
	}

	/**
	 * Gets the group fragment id for member tags.
	 *
	 * @return Current value of the group fragment
	 * @see Tags
	 */
	public long getTagsId() {
		return (getTags() == null ? null : getTags().getId());
	}

	/**
	 * Sets the group fragment id from member tags.
	 *
	 * @param aId New value for the group fragment
	 * @see Tags
	 */
	public void setTagsId(long aId) {
		if (getTags() != null) {
			getTags().setId(aId);
		}
	}

}
