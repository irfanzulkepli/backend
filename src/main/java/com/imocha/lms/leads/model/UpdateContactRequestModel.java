package com.imocha.lms.leads.model;

import java.util.List;

import lombok.Data;

@Data
public class UpdateContactRequestModel {

	private List<ContactRequest> emails;
	private List<ContactRequest> phones;
}
