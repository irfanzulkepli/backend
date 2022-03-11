package com.imocha.lms.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.common.entities.PhoneEmailTypes;
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
}
