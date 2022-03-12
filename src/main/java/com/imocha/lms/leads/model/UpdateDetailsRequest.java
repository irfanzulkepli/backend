package com.imocha.lms.leads.model;

import lombok.Data;

@Data
public class UpdateDetailsRequest {

	private String name;
	private Long contactTypesId;
	private Long ownerId;
}
