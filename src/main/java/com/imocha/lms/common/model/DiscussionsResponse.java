package com.imocha.lms.common.model;

import java.util.Date;

import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
public class DiscussionsResponse {

    private Long id;
    private long commentableId;
    private String commentBody;
    private Date createdAt;
    private Date updatedAt;
    private Users users;
}
