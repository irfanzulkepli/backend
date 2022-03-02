package com.imocha.lms.common.model;

import java.util.Date;

import lombok.Data;

@Data
public class EmailResponse {

    private Long id;
    private String value;
    private Long typeId;
    private String contextableType;
    private Long contextableId;
    private Date createdAt;
    private Date updatedAt;
}
