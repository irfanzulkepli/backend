package com.imocha.lms.leads.model;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class UpdatePersonOrganizationRequest {

	private Long peopleId;
	private Long organizationId;

	@Nullable
	private String jobTitle;
}
