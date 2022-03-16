package com.imocha.lms.deals.pipelines.model;

import java.util.List;

import lombok.Data;

@Data
public class UpdatePipelinesRequest {
    private long id;
    private String name;
    private List<UpdateStageRequest> stages;
}
