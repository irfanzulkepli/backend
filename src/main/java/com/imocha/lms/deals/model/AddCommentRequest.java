package com.imocha.lms.deals.model;

import java.util.Date;


import lombok.Data;

@Data
public class AddCommentRequest {
    private long userId;
    private Date createdAt;
    private long commentableId;
    private String commentBody;
}
