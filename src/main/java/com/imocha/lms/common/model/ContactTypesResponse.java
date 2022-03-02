package com.imocha.lms.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class ContactTypesResponse {

    private long id;
    private String name;
    private String clazz;
    private Date createdAt;
    private Date updatedAt;
}
