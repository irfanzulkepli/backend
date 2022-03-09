package com.imocha.lms.common.model;

import java.util.List;

import com.imocha.lms.leads.entities.People;
import com.imocha.lms.leads.model.OwnerResponse;
import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
public class ActivityResponse {

	private Long id;
	private String title;
	private String description;
	private List<People> participants;
	private List<Users> collaborators;
	private OwnerResponse createdBy;
}
