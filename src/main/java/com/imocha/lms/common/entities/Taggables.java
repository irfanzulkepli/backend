// Generated with g9.

package com.imocha.lms.common.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.imocha.lms.common.entities.Taggables.TaggablesId;
import com.imocha.lms.common.enumerator.ContextableTypes;

import lombok.Data;

@Data
@Entity
@Table(name = "taggables", indexes = {
		@Index(name = "taggablesTaggablesTagIdTaggableTypeTaggableIdUnique", columnList = "tag_id,taggable_type,taggable_id", unique = true),
		@Index(name = "taggablesTaggablesTaggableTypeTaggableIdIndex", columnList = "taggable_type,taggable_id") })
@IdClass(TaggablesId.class)
public class Taggables implements Serializable {

	public static class TaggablesId implements Serializable {
		Long taggableId;
		Long tags;
		ContextableTypes taggableType;
	}

	@Id
	@Column(name = "taggable_type", length = 191)
	private ContextableTypes taggableType;

	@Id
	@Column(name = "taggable_id", precision = 20)
	private long taggableId;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "tag_id", nullable = false)
	private Tags tags;

}
