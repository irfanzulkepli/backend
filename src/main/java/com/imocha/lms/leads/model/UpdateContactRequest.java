package com.imocha.lms.leads.model;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class UpdateContactRequest {

	private Long id;

	private String value;

	@Nullable
	private Long typeId;
}
