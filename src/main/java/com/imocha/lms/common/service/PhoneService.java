package com.imocha.lms.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.common.model.PhoneResponse;
import com.imocha.lms.common.repositories.PhonesRepository;

@Service
public class PhoneService {

    @Autowired
    private PhonesRepository phonesRepository;

    public List<PhoneResponse> getPersonPhoneById(Long id) {
        return this.phonesRepository.findByContextableTypeIgnoreCaseContainingAndContextableId("person", id).stream()
                .map(phone -> {
                    PhoneResponse phoneResponse = new PhoneResponse();
                    BeanUtils.copyProperties(phone, phoneResponse);
                    return phoneResponse;
                }).collect(Collectors.toList());
    }
}
