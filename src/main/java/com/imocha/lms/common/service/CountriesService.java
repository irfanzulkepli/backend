package com.imocha.lms.common.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.imocha.lms.common.entities.Countries;
import com.imocha.lms.common.model.CountriesResponse;
import com.imocha.lms.common.repositories.CountriesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CountriesService {

	@Autowired
	CountriesRepository countriesRepository;

	public List<CountriesResponse> list() {
		List<Countries> countries = countriesRepository.findAll();

		List<CountriesResponse> countriesRes = countries.stream().map(country -> {
			CountriesResponse countryResponse = new CountriesResponse();
			BeanUtils.copyProperties(country, countryResponse);

			return countryResponse;
		}).collect(Collectors.toList());

		return countriesRes;
	}

	public Countries getCountryById(Long id) {
		Optional<Countries> countryOptional = countriesRepository.findById(id);
		return countryOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
	}
}
