package com.imocha.lms.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.common.entities.Emails;
import com.imocha.lms.common.model.EmailResponse;
import com.imocha.lms.common.repositories.EmailsRepository;

@Service
public class EmailService {

	@Autowired
	private EmailsRepository emailsRepository;

	public List<EmailResponse> getPersonEmailById(Long id) {
		return this.emailsRepository.findByContextableTypeIgnoreCaseContainingAndContextableId("person", id).stream()
				.map(email -> {
					EmailResponse emailResponse = new EmailResponse();
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
