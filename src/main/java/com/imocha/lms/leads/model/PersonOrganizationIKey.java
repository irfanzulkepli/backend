package com.imocha.lms.leads.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonOrganizationIKey implements Serializable {

	private int organizations;
	private int people;
}
