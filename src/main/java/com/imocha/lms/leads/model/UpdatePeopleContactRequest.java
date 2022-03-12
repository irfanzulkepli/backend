package com.imocha.lms.leads.model;

import lombok.Data;

@Data
public class UpdatePeopleContactRequest {

	private UpdateRequestModel emails;
	private UpdateRequestModel phones;
}
