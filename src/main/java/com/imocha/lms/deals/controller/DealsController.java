package com.imocha.lms.deals.controller;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.deals.model.DealsResponse;
import com.imocha.lms.deals.model.UpdateDealsRequest;
import com.imocha.lms.deals.service.DealsService;

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
@RequestMapping("deals")
public class DealsController {
	@Autowired
	private DealsService dealsService;

	@GetMapping("page")
	public Page<DealsResponse> page(@Valid PageableRequest pageableRequest) {
		return dealsService.page(pageableRequest);
	}

	@GetMapping("/{id}")
	public DealsResponse get(@PathVariable long id) {
		return dealsService.getDealsResponse(id);
	}

	@PutMapping()
	public long update(@RequestBody UpdateDealsRequest request) {
		return dealsService.update(request);
	}

	@DeleteMapping("/{id}")
	public long delete(@PathVariable long id){
		return dealsService.delete(id);
	}
}
