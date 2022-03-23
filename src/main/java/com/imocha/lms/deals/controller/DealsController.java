package com.imocha.lms.deals.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.imocha.common.model.PageableRequest;
import com.imocha.lms.common.entities.Discussions;
import com.imocha.lms.common.model.DiscussionsResponse;
import com.imocha.lms.common.service.DiscussionsService;
import com.imocha.lms.deals.entities.Deals;
import com.imocha.lms.deals.model.AddCommentRequest;
import com.imocha.lms.deals.model.AddDealsRequest;
import com.imocha.lms.deals.model.DealsListResponse;
import com.imocha.lms.deals.model.DealsPageResponse;
import com.imocha.lms.deals.model.DealsResponse;
import com.imocha.lms.deals.model.UpdateCommentRequest;
import com.imocha.lms.deals.model.UpdateDealsRequest;
import com.imocha.lms.deals.model.UpdateDealsTagRequest;
import com.imocha.lms.deals.model.UpdateDealsToLostRequest;
import com.imocha.lms.deals.service.DealsService;
import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.FollowerResponse;
import com.imocha.lms.leads.model.UpdateFollowerRequest;

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
	public Page<DealsPageResponse> page(@Valid PageableRequest pageableRequest) {
		return dealsService.page(pageableRequest);
	}

	@GetMapping({ "list/pipeline", "list/pipeline/{id}" })
	public List<DealsListResponse> listPipelineView(@PathVariable(required = false) String id) {
		return dealsService.listPipelineView(id);
	}

	@GetMapping("/{id}")
	public DealsResponse get(@PathVariable long id) {
		return dealsService.getDealsResponse(id);
	}

	@GetMapping("{id}/followers/page")
	public Page<FollowerResponse> pageFollowers(@Valid PageableRequest pageableRequest, @PathVariable("id") Long id) {
		return dealsService.getFollowersByDealsId(id, pageableRequest);
	}

	@GetMapping("{id}/comments/list")
	public List<DiscussionsResponse> listComments(@PathVariable("id") Long id) {
		return dealsService.getCommentsByDealsId(id);
	}

	@PutMapping("/{id}/comments")
	public Discussions updateComment(@RequestBody UpdateCommentRequest requestModel, @PathVariable("id") Long id) {
		return dealsService.updateComment(id, requestModel);
	}

	@PutMapping("/{id}/followers")
	public Deals updateFollowers(@RequestBody UpdateFollowerRequest requestModel, @PathVariable("id") Long id) {
		return dealsService.updateFollowers(requestModel, id);
	}

	@PutMapping("/lost/{id}")
	public long updateDealsToLost(@PathVariable long id, @RequestBody UpdateDealsToLostRequest request) {
		return dealsService.updateDealsToLost(id, request);
	}

	@PutMapping("/won/{id}")
	public long updateDealsToLost(@PathVariable long id) {
		return dealsService.updateDealsToWon(id);
	}

	@PutMapping("/tag/{id}")
	public long updateTag(@PathVariable long id, @RequestBody UpdateDealsTagRequest request) {
		return dealsService.updateDealsTag(id, request);
	}

	@PutMapping("/stage/{oldId}/{newId}")
	public long updateAllDealsToNewStage(@PathVariable long oldId, @PathVariable long newId) {
		return dealsService.updateAllDealsToNewStage(oldId, newId);
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

	@PostMapping("/comments")
	public Discussions addComment(@RequestBody AddCommentRequest requestModel) {
		return dealsService.addComment(requestModel);
	}

	@DeleteMapping("/{id}/comments")
	public long deleteCommentById(@PathVariable long id) {
		return dealsService.deleteCommentById(id);
	}

	@DeleteMapping("/{id}")
	public long delete(@PathVariable long id) {
		return dealsService.delete(id);
	}
}
