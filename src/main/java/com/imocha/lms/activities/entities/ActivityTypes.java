// Generated with g9.

package com.imocha.lms.activities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.imocha.common.audit.Auditable;

import lombok.Data;

@Data
@Entity(name = "activity_types")
public class ActivityTypes extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 20)
	private long id;
	@Column(nullable = false, length = 191)
	private String name;
	@Column(length = 191)
	private String icon;

}
