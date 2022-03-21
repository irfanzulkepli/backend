package com.imocha.lms.common.service;

import java.util.List;
import java.util.stream.Collectors;

import com.imocha.lms.common.entities.Emails;
import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.common.model.EmailResponse;
import com.imocha.lms.common.repositories.EmailsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private EmailsRepository emailsRepository;

	public List<EmailResponse> getPersonEmailById(Long id) {
		return this.emailsRepository.findByContextableTypeAndContextableId(ContextableTypes.PERSON, id).stream()
				.map(email -> {
					EmailResponse emailResponse = new EmailResponse();
					emailResponse.setType(email.getContactTypes());
					BeanUtils.copyProperties(email, emailResponse);
					return emailResponse;
				}).collect(Collectors.toList());
	}

	public Emails getById(Long id) {
		return emailsRepository.getById(id);
	}

	public Emails save(Emails email) {
		return emailsRepository.save(email);
	}

	public void deleteById(Long id) {
		emailsRepository.deleteById(id);
	}
}
