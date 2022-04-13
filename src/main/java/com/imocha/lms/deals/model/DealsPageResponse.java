package com.imocha.lms.deals.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.imocha.lms.common.model.StatusesResponse;
import com.imocha.lms.common.model.TagResponse;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
public class DealsPageResponse {
    private long id;
    private String title;
    private long value;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    private Users createdBy;
    private OwnerResponse owner;
    private StatusesResponse statuses;

    private List<TagResponse> tags;

}
