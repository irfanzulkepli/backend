package com.imocha.lms.deals.model;

import com.imocha.lms.common.entities.Statuses;
import com.imocha.lms.common.enumerator.ContextableTypes;

import lombok.Data;

@Data
public class DealStatusCountById {
	private ContextableTypes contextableType;
	private long contextableId;
	private Integer total;
	private Statuses statuses;
}
