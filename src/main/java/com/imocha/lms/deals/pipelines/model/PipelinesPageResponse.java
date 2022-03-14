package com.imocha.lms.deals.pipelines.model;

import java.util.Date;

import com.imocha.lms.leads.model.OwnerResponse;

import lombok.Data;

@Data
public class PipelinesPageResponse {
	private long id;
	private String name;
	private long totalDealValue;
    private String active;
	private int dealsCount;
	private int stagesCount;
	private Date createdAt;
	private Date updatedAt;
	private OwnerResponse users;
}
