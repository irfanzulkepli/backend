package com.imocha.lms.deals.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imocha.lms.common.enumerator.ContextableTypes;

import lombok.Data;

@Data
public class AddDealsRequest {
	private String title;
	private long value;
	private String description;
    private long ownerId;
    private long pipelinesId;
    private long stagesId;
	private ContextableTypes contextableType;
    private long contactPersonId;
    private long personId;
    private long organizationId;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expiredAt;
}
