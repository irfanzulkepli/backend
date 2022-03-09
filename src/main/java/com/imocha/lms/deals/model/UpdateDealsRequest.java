package com.imocha.lms.deals.model;

import java.util.Date;

import lombok.Data;

@Data
public class UpdateDealsRequest {
    private long id;
    private String title;
    private String description;
    private long createdById;
    private long value;
    private long pipelinesId;
    private long stagesId;
    private Date expiredAt;
    private long ownerId;
}
