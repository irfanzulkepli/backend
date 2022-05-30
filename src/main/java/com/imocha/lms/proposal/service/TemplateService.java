package com.imocha.lms.proposal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imocha.lms.proposal.entities.Template;
import com.imocha.lms.proposal.model.OffsetBasedPageRequest;
import com.imocha.lms.proposal.model.TemplateResponse;
import com.imocha.lms.proposal.model.UpdateTemplateRequest;
import com.imocha.lms.proposal.repositories.TemplateMapper;
import com.imocha.lms.proposal.repositories.TemplateRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TemplateService {

	@Autowired
	TemplateRepository templateRepository;

	@Autowired
	TemplateMapper templateMapper;
	

	public ResponseEntity<?> create(Template template) {
		
		try {
			
			Template result = templateRepository.save(template);
			
			TemplateResponse templateResponse = templateMapper.templateToTemplateResponse(result);
			
			return new ResponseEntity<>(templateResponse, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	

	
	public ResponseEntity<?> update(UpdateTemplateRequest updateTemplateRequest) {
		
		Optional<Template> templateOptional = templateRepository.findById(updateTemplateRequest.getId());
		
		templateOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
				"template with id = "+updateTemplateRequest.getId()+" cannot be updated since not exist!"));
		
		//Template template = templateOptional.get();
		//BeanUtils.copyProperties(updateTemplateRequest, template);
		
		Template updatedTemplate = templateMapper.updateTemplateRequestToTemplate(updateTemplateRequest);
		
		Template savedTemplate = templateRepository.save(updatedTemplate);
		
		TemplateResponse templateResponse = templateMapper.templateToTemplateResponse(savedTemplate);
		
		return new ResponseEntity<>(templateResponse,HttpStatus.OK);
		
	}
	

	
	public ResponseEntity<?> delete(int id) {
		
		Optional<Template> templateOptional = templateRepository.findById(id);
		
		templateOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
				"template with id = "+id+" cannot be deleted since not exist!"));
		
		templateRepository.deleteById(id);
		
		return new ResponseEntity<>("Template with id= " + id + " has been deleted", HttpStatus.OK);
		
	}
	
	
	
	public ResponseEntity<?> getById(int id) {
		
		Optional<Template> resultOptional = templateRepository.findById(id);
		
		resultOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "template with id = "+id+" not exist!"));
		
		Template result = resultOptional.get();
		
		TemplateResponse templateResponse = templateMapper.templateToTemplateResponse(result);
		
		return new ResponseEntity<>(templateResponse, HttpStatus.OK);
		
	}

	
	
	public ResponseEntity<?> get(String title, int limit, int offset, String field, Direction order) {
		
		try {

			List<Template> templates = new ArrayList<Template>();

			Pageable paging;
			if (order == Direction.ASC) {
				//paging = (Pageable) PageRequest.of(page, size, Sort.by(field).ascending());
				paging= (Pageable) new OffsetBasedPageRequest(limit,offset,Sort.by(field).ascending());
			} else {
				//paging = (Pageable) PageRequest.of(page, size, Sort.by(field).descending());
				paging= (Pageable) new OffsetBasedPageRequest(limit,offset,Sort.by(field).descending());
			}

			Page<Template> pageResult;

			if (title == null) {
				pageResult = templateRepository.findAll(paging);
			} else {
				pageResult = templateRepository.findByTitleContaining(title, paging);
			}

			templates = pageResult.getContent();

			Map<String, Object> response = new HashMap<>();
			Map<String, Object> pageData = new HashMap<>();
			response.put("data", templates);

			pageData.put("search", title == null ? "" : title);
			pageData.put("sortField", field);
			pageData.put("sortOrder", order.toString());
			pageData.put("currentPage", pageResult.getNumber()+1);
			pageData.put("totalPages", pageResult.getTotalPages());
			pageData.put("total", pageResult.getTotalElements());

			response.put("page", pageData);

			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
}
