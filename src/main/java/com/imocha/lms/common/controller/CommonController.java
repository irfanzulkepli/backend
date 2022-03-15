package com.imocha.lms.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.common.model.CountriesResponse;
import com.imocha.lms.common.model.TagResponse;
import com.imocha.lms.common.service.CountriesService;
import com.imocha.lms.common.service.PhoneEmailService;
import com.imocha.lms.common.service.TagService;

@RestController
@RequestMapping("common")
public class CommonController {

	@Autowired
	private CountriesService countriesService;

	@Autowired
	private TagService tagService;

	@Autowired
	private PhoneEmailService phoneEmailService;

	@GetMapping("countries/list")
	public List<CountriesResponse> getAllCountries() {
		return countriesService.list();
	}

	@GetMapping("tags/list")
	public List<TagResponse> getAllTags() {
		return tagService.list();
	}

	@GetMapping("phoneEmailTypes/list")
	public List<ContactTypesResponse> getAllPhoneEmailTypes() {
		return phoneEmailService.list();
	}
}
