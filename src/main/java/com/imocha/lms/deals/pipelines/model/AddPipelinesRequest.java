package com.imocha.lms.deals.pipelines.model;

import java.util.List;

import lombok.Data;

@Data
public class AddPipelinesRequest {
    private String name;
    private List<UpdateStageRequest> stages;
}
