package com.imocha.lms.deals.pipelines.controller;

import java.util.List;

import com.imocha.lms.deals.pipelines.model.StagesListResponse;
import com.imocha.lms.deals.pipelines.service.StagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stages")
public class StagesController {

    @Autowired
    private StagesService stagesService;

    @GetMapping("list/pipelines/{id}")
    public List<StagesListResponse> listByPipelinesId(@PathVariable long id) {
        return this.stagesService.listByPipelinesId(id);
    }
}
