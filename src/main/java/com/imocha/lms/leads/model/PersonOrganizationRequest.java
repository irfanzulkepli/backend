package com.imocha.lms.leads.model;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class PersonOrganizationRequest {

	private Long id;

	@Nullable
	private String jobTitle;
}
