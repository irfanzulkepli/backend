package com.imocha.lms.stages.controller;

import java.util.List;

import com.imocha.lms.stages.model.StagesListResponse;
import com.imocha.lms.stages.service.StagesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stages")
public class StagesController {

    @Autowired
    private StagesService stagesService;

    @GetMapping("list")
    public List<StagesListResponse> list() {
        return this.stagesService.list();
    }
}
