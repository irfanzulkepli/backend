package com.imocha.lms.deals.model;

import java.util.Date;

import com.imocha.lms.leads.model.OwnerResponse;

import lombok.Data;

@Data
public class LostReasonsPageResponse {
    private long id;
    private String lostReason;
    private String active;
    private Date createdAt;
    private Date updatedAt;
    private OwnerResponse createdBy;
}
