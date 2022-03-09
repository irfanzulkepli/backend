package com.imocha.lms.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class StatusesResponse {
    private long id;
    private String name;
    private String class_;
    private String type;
    private Date createdAt;
    private Date updatedAt;
}
