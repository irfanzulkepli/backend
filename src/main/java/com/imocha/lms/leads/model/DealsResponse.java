package com.imocha.lms.leads.model;

import lombok.Data;

@Data
public class DealsResponse {

	private Long id;
	private Long value;
	private String title;
	private OwnerResponse owner;
}
