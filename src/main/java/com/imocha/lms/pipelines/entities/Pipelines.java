// Generated with g9.

package com.imocha.lms.pipelines.entities;

import java.io.Serializable;
import java.util.Date;
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

import com.imocha.lms.stages.entities.Stages;
import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
@Entity
@Table(name = "pipelines", indexes = { @Index(name = "pipelines_name_IX", columnList = "name", unique = true) })
public class Pipelines implements Serializable {

	/** Primary key. */
	protected static final String PK = "id";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 20)
	private long id;
	@Column(unique = true, nullable = false, length = 191)
	private String name;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;
	@ManyToOne
	@JoinColumn(name = "created_by")
	private Users users;
	@OneToMany(mappedBy = "pipelines")
	private Set<Stages> stages;
}
