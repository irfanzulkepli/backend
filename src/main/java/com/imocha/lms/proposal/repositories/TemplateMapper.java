package com.imocha.lms.proposal.repositories;

import org.mapstruct.Mapper;

import com.imocha.lms.proposal.entities.Template;
import com.imocha.lms.proposal.model.TemplateResponse;
import com.imocha.lms.proposal.model.UpdateTemplateRequest;

@Mapper(componentModel="spring")
public interface TemplateMapper {
	
	TemplateResponse templateToTemplateResponse(Template template);
	
	Template updateTemplateRequestToTemplate(UpdateTemplateRequest updateTemplateRequest);

}
