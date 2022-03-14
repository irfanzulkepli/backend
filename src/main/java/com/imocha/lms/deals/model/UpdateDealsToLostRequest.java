package com.imocha.lms.deals.model;

import lombok.Data;

@Data
public class UpdateDealsToLostRequest {
    private long lostReasonsId;
    private String comment;
}
