package com.imocha.lms.deals.model;

import com.imocha.lms.common.entities.Statuses;

import lombok.Data;

@Data
public class DealStatusCountById {

	private String contextableType;
	private long contextableId;
	private Integer total;
	private Statuses statuses;
}
