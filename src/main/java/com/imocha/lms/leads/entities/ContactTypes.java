package com.imocha.lms.leads.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "contact_types")
public class ContactTypes implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 20)
    private long id;
    @Column(nullable = false, length = 191)
    private String name;
    @Column(name = "class", length = 191)
    private String clazz;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    
    public ContactTypes() {}
    
    ContactTypes(Long id, String name, String clazz, Date createdAt, Date updatedAt ) {
    	this.id = id;
    	this.name = name;
    	this.clazz = clazz;
    	this.createdAt = createdAt;
    	this.updatedAt = updatedAt;
    }
    
}