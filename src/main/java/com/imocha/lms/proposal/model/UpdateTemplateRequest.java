package com.imocha.lms.proposal.model;

import lombok.Data;

@Data
public class UpdateTemplateRequest {
	
	private int id;
	private String title;
	private String content;
	private String status;

}
