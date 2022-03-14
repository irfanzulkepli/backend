package com.imocha.lms.deals.controller;

import java.util.List;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.deals.model.AddLostReasonsRequest;
import com.imocha.lms.deals.model.LostReasonsListResponse;
import com.imocha.lms.deals.model.LostReasonsPageResponse;
import com.imocha.lms.deals.model.UpdateLostReasonsRequest;
import com.imocha.lms.deals.service.LostReasonsService;

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
@RequestMapping("lost-reasons")
public class LostReasonsController {
    @Autowired
    private LostReasonsService lostReasonsService;

    @GetMapping("page")
    public Page<LostReasonsPageResponse> page(@Valid PageableRequest pageableRequest) {
        return lostReasonsService.page(pageableRequest);
    }

    @GetMapping("list")
    public List<LostReasonsListResponse> list() {
        return lostReasonsService.list();
    }

    @PostMapping()
    public long add(@RequestBody AddLostReasonsRequest request) {
        return lostReasonsService.add(request);
    }

    @PutMapping("/{id}")
    public long update(@PathVariable long id, @RequestBody UpdateLostReasonsRequest request) {
        return lostReasonsService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public long delete(@PathVariable long id) {
        return lostReasonsService.delete(id);
    }
}
