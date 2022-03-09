package com.imocha.lms.lostReasons.model;

import java.util.Date;

import com.imocha.lms.leads.model.OwnerResponse;

import lombok.Data;

@Data
public class LostReasonsResponse {
    private long id;
    private String lostReason;
    private Date createdAt;
    private Date updatedAt;
    private OwnerResponse users;
}