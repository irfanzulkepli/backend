package com.imocha.lms.deals.model;

import com.imocha.common.model.PageableRequest;

import lombok.Data;

@Data
public class DealPageRequest extends PageableRequest {
    private String search;
}
