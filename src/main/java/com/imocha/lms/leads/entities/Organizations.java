package com.imocha.lms.leads.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.common.audit.Auditable;
import com.imocha.lms.common.entities.Countries;
import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
@Entity(name = "organizations")
public class Organizations extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 20)
	private long id;
	@Column(nullable = false, length = 191)
	private String name;
	private String address;
	private String area;
	@Column(length = 191)
	private String state;
	@Column(length = 191)
	private String city;
	@Column(name = "zip_code", length = 191)
	private String zipCode;

	@Column(name = "active")
	private boolean active = Boolean.TRUE;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Countries countries;

	@ManyToOne
	@JoinColumn(name = "contact_type_id")
	private ContactTypes contactTypes;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Users owner;
}
