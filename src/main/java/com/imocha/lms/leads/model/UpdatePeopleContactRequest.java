package com.imocha.lms.leads.model;

import lombok.Data;

@Data
public class UpdatePeopleContactRequest {

	private UpdateContactRequestModel emails;
	private UpdateContactRequestModel phones;
}
