package com.imocha.lms.leads.model;

import lombok.Data;

@Data
public class DeletePersonOrganizationRequest {

	private Long peopleId;
	private Long organizationId;
}
