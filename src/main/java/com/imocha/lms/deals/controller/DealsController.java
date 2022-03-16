package com.imocha.lms.deals.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.deals.model.AddDealsRequest;
import com.imocha.lms.deals.model.DealsResponse;
import com.imocha.lms.deals.model.UpdateDealsRequest;
import com.imocha.lms.deals.model.UpdateDealsToLostRequest;
import com.imocha.lms.deals.service.DealsService;

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
@RequestMapping("deals")
public class DealsController {
	@Autowired
	private DealsService dealsService;

	@GetMapping("page")
	public Page<DealsResponse> page(@Valid PageableRequest pageableRequest) {
		return dealsService.page(pageableRequest);
	}

	@GetMapping("list/pipeline-view/{id}")
	public List<DealsResponse> listPipelineView(@PathVariable long id) {
		return dealsService.listPipelineView(id);
	}

	@GetMapping("/{id}")
	public DealsResponse get(@PathVariable long id) {
		return dealsService.getDealsResponse(id);
	}

	@PutMapping("/lost/{id}")
	public long updateDealsToLost(@PathVariable long id, @RequestBody UpdateDealsToLostRequest request) {
		return dealsService.updateDealsToLost(id, request);
	}

	@PutMapping("/won/{id}")
	public long updateDealsToLost(@PathVariable long id) {
		return dealsService.updateDealsToWon(id);
	}

	@PutMapping("/{id}")
	public long update(@PathVariable long id, @RequestBody UpdateDealsRequest request) {
		return dealsService.update(id, request);
	}

	@PutMapping("/{id}/stage")
	public long updateStage(@PathVariable long id, @RequestBody long stagesId) {
		return dealsService.updateStage(id, stagesId);
	}

	@PutMapping("/{id}/value")
	public long updateValue(@PathVariable long id, @RequestBody long dealValue) {
		return dealsService.updateValue(id, dealValue);
	}

	@PutMapping("/{id}/person")
	public long updatePersonId(@PathVariable long id, @RequestBody long personId) {
		return dealsService.updatePersonId(id, personId);
	}

	@PutMapping("/{id}/expired")
	public long updateExpiredDate(@PathVariable long id, @RequestBody Date expiredDate) {
		return dealsService.updateExpiredDate(id, expiredDate);
	}

	@PutMapping("/{id}/description")
	public long updateDescription(@PathVariable long id, @RequestBody String description) {
		return dealsService.updateDescription(id, description);
	}

	@PostMapping()
	public long add(@RequestBody AddDealsRequest request) {
		return dealsService.addDeals(request);
	}

	@DeleteMapping("/{id}")
	public long delete(@PathVariable long id) {
		return dealsService.delete(id);
	}
}
