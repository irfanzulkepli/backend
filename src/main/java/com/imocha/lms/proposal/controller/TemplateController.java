package com.imocha.lms.proposal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imocha.lms.proposal.entities.Template;
import com.imocha.lms.proposal.model.TemplateResponse;
import com.imocha.lms.proposal.model.UpdateTemplateRequest;
import com.imocha.lms.proposal.service.TemplateService;

@RestController
@RequestMapping("lms/proposal/template")
public class TemplateController {

	@Autowired
	TemplateService templateService;

	@PostMapping("/create")
	public ResponseEntity<?> createTemplate(@RequestBody Template template) {
		return templateService.create(template);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateTemplate(@RequestBody UpdateTemplateRequest UpdateTemplateRequest) {
		return templateService.update(UpdateTemplateRequest);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		return templateService.getById(id);
	}

	@GetMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
		return templateService.delete(id);
	}

	@GetMapping(value = "/gettemplate")
	public ResponseEntity<?> getAllTemplate(
			@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "1") int limit,
			@RequestParam(defaultValue = "0") int offset,
			@RequestParam(defaultValue = "title") String field,
			@RequestParam(defaultValue = "ascending") String direction
			) {
		Direction order;
		if (direction.equals("ascending")) {
			order = Direction.ASC;
		} else {
			order = Direction.DESC;
		}

		return templateService.get(title, limit, offset, field, order);
	}

}
