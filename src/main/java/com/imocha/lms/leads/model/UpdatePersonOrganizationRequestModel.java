package com.imocha.lms.leads.model;

import java.util.List;

import lombok.Data;

@Data
public class UpdatePersonOrganizationRequestModel {

	private List<PersonOrganizationRequest> personOrganizations;
}
