package com.imocha.lms.common.service;

import java.util.List;
import java.util.stream.Collectors;

import com.imocha.lms.common.entities.Phones;
import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.common.model.PhoneResponse;
import com.imocha.lms.common.repositories.PhonesRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

	@Autowired
	private PhonesRepository phonesRepository;

	public List<PhoneResponse> getPersonPhoneById(Long id) {
		return this.phonesRepository.findByContextableTypeAndContextableId(ContextableTypes.PERSON, id).stream()
				.map(phone -> {
					PhoneResponse phoneResponse = new PhoneResponse();
					phoneResponse.setType(phone.getContactTypes());
					BeanUtils.copyProperties(phone, phoneResponse);
					return phoneResponse;
				}).collect(Collectors.toList());
	}

	public Phones save(Phones phone) {
		return phonesRepository.save(phone);
	}

	public void deleteById(Long id) {
		phonesRepository.deleteById(id);
	}
}
