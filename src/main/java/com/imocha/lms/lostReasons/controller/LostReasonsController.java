package com.imocha.lms.lostReasons.controller;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.lostReasons.model.LostReasonsResponse;
import com.imocha.lms.lostReasons.model.UpdateLostReasonsRequest;
import com.imocha.lms.lostReasons.service.LostReasonsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Page<LostReasonsResponse> page(@Valid PageableRequest pageableRequest) {
        return lostReasonsService.page(pageableRequest);
    }

    @PutMapping()
    public long update(@RequestBody UpdateLostReasonsRequest request) {
        return lostReasonsService.update(request);
    }

    @DeleteMapping("/{id}")
    public long delete(@PathVariable long id) {
        return lostReasonsService.delete(id);
    }
}
