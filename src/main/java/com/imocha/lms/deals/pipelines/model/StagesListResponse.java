package com.imocha.lms.deals.pipelines.model;

import lombok.Data;

@Data
public class StagesListResponse {
    private long id;
    private String name;
    private int probability;
    private int priority;
    private PipelinesResponse pipelines;
}
