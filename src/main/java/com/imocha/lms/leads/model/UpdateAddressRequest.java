package com.imocha.lms.leads.model;

import lombok.Data;

@Data
public class UpdateAddressRequest {

	private String address;
	private String area;
	private String city;
	private String state;
	private String zipCode;
	private Long countryId;
}
