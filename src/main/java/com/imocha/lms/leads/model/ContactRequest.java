package com.imocha.lms.leads.model;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class ContactRequest {

	private String value;

	@Nullable
	private Long typeId;

	private String contextableType;

}
