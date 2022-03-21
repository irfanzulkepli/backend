package com.imocha.lms.deals.model;

import java.util.Date;

import com.imocha.lms.common.enumerator.ContextableTypes;

import lombok.Data;

@Data
public class UpdateDealsRequest {
    private String title;
    private String description;
	private ContextableTypes contextableType;
    private long personId;
    private long organizationId;
    private long value;
    private long pipelinesId;
    private long stagesId;
    private Date expiredAt;
    private long ownerId;
}
