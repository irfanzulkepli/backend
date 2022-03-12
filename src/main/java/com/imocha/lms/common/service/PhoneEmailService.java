package com.imocha.lms.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.common.entities.PhoneEmailTypes;
import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.common.repositories.PhoneEmailTypesRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PhoneEmailService {

	@Autowired
	private PhoneEmailTypesRepository phoneEmailTypesRepository;

	public PhoneEmailTypes get(Long id) {
		return phoneEmailTypesRepository.getById(id);
	}

	public List<ContactTypesResponse> list() {
		List<PhoneEmailTypes> phoneEmailTypes = phoneEmailTypesRepository.findAll();
		List<ContactTypesResponse> contactTypesResponses = phoneEmailTypes.stream().map(phoneEmailType -> {
			ContactTypesResponse contactTypesResponse = new ContactTypesResponse();
			BeanUtils.copyProperties(phoneEmailType, contactTypesResponse);
			contactTypesResponse.setClazz(phoneEmailType.getClass_());

			return contactTypesResponse;
		}).collect(Collectors.toList());

		return contactTypesResponses;
	}
}
