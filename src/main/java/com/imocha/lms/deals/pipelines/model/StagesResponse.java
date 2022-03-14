package com.imocha.lms.deals.pipelines.model;

import java.util.Date;

import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
public class StagesResponse {
    private long id;
    private String name;
    private int probability;
    private int priority;
    private Date createdAt;
    private Date updatedAt;
    private Users users;
}
