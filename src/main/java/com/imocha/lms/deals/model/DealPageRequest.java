package com.imocha.lms.deals.model;

import java.util.HashSet;
import java.util.Set;

import com.imocha.common.model.PageableRequest;

import lombok.Data;

@Data
public class DealPageRequest extends PageableRequest {
    private String search;
    private String pipelineId;
	private Set<Long> tagIds = new HashSet<Long>();
    private String dateFrom;
    private String dateTo;
    private int hasProposals = 0;
}
