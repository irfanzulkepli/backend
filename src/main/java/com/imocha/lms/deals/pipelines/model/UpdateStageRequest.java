package com.imocha.lms.deals.pipelines.model;

import lombok.Data;

@Data
public class UpdateStageRequest {
    private long id;
    private String name;
    private int probability;
    private int priority;
}
