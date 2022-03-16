package com.imocha.lms.deals.pipelines.controller;

import java.util.List;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.deals.pipelines.model.AddPipelinesRequest;
import com.imocha.lms.deals.pipelines.model.PipelinesListResponse;
import com.imocha.lms.deals.pipelines.model.PipelinesPageResponse;
import com.imocha.lms.deals.pipelines.model.UpdatePipelinesRequest;
import com.imocha.lms.deals.pipelines.service.PipelinesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pipelines")
public class PipelinesController {
    @Autowired
    private PipelinesService pipelinesSerive;

    @GetMapping("page")
    public Page<PipelinesPageResponse> page(@Valid PageableRequest pageableRequest) {
        return pipelinesSerive.page(pageableRequest);
    }

    @GetMapping("list")
    public List<PipelinesListResponse> list() {
        return pipelinesSerive.list();
    }

    @PostMapping()
    public long create(@RequestBody AddPipelinesRequest request) {
        return pipelinesSerive.add(request);
    }

    @PutMapping("/{id}")
    public long update(@PathVariable long id, @RequestBody UpdatePipelinesRequest request) {
        return pipelinesSerive.update(request);
    }

    @DeleteMapping("/{id}")
    public long delete(@PathVariable long id) {
        return pipelinesSerive.delete(id);
    }
}
