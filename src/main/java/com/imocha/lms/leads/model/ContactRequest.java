package com.imocha.lms.leads.model;

import com.imocha.lms.common.enumerator.ContextableTypes;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class ContactRequest {

	private String value;

	@Nullable
	private Long typeId;

	private ContextableTypes contextableType;

}
