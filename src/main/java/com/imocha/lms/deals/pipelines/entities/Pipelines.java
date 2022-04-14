// Generated with g9.

package com.imocha.lms.deals.pipelines.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.imocha.common.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "pipelines", indexes = { @Index(name = "pipelines_name_IX", columnList = "name", unique = true) })
public class Pipelines extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 20)
	private long id;
	@Column(unique = true, nullable = false, length = 191)
	private String name;
	@Column(name = "active")
	private boolean active = true;
}
