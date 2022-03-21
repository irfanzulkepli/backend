package com.imocha.lms.common.model;

import com.imocha.lms.common.entities.PhoneEmailTypes;
import com.imocha.lms.common.enumerator.ContextableTypes;

import lombok.Data;

@Data
public class EmailResponse {

	private Long id;
	private String value;
	private Long typeId;
	private ContextableTypes contextableType;
	private Long contextableId;
	private PhoneEmailTypes type;
}
