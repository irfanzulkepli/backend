package com.imocha.lms.leads.model;

import java.util.Date;
import java.util.List;

import com.imocha.lms.common.model.ContactTypesResponse;
import com.imocha.lms.common.model.EmailResponse;
import com.imocha.lms.common.model.PhoneResponse;

import lombok.Data;

@Data
public class PersonPageResponse {

    private Long id;
    private String name;
    private String address;
    private Date createdAt;
    private Date updatedAt;
    private String area;
    private String state;
    private String city;
    private String zipCode;

    private ContactTypesResponse contactTypes;

    private List<EmailResponse> emails;

    private List<PhoneResponse> phones;

    private OwnerResponse owner;
}
