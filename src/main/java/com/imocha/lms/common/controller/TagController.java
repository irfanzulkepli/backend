package com.imocha.lms.common.controller;

import java.util.List;

import com.imocha.lms.common.model.TagResponse;
import com.imocha.lms.common.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("list")
    public List<TagResponse> list(){
        return tagService.list();
    }
}
