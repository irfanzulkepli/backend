package com.imocha.lms.leads.model;

import java.util.List;

import lombok.Data;

@Data
public class UpdateRequestModel {

	private List<Long> delete;
	private List<UpdateContactRequest> update;
	private List<ContactRequest> add;
}
