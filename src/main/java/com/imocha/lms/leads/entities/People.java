// Generated with g9.

package com.imocha.lms.leads.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.lms.leads.entities.ContactTypes;
import com.imocha.lms.entities.Countries;
import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
@Entity(name = "people")
public class People implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 20)
    private Long id;
    @Column(nullable = false, length = 191)
    private String name;
    private String address;
//    @Column(name = "contact_type_id", nullable = false, precision = 20)
//    private long contactTypeId;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    private String area;
    @Column(length = 191)
    private String state;
    @Column(length = 191)
    private String city;
    @Column(name = "zip_code", length = 191)
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "attach_login_user_id")
    private Users attachLoginUser;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Countries countries;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Users owner;

    @ManyToOne
    @JoinColumn(name = "contact_type_id")
    private ContactTypes contactTypes;
}
