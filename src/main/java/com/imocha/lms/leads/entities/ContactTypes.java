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
    
    ContactTypes() {}
    
    ContactTypes(Long id, String name, String clazz, Date createdAt, Date updatedAt ) {
    	this.id = id;
    	this.name = name;
    	this.clazz = clazz;
    	this.createdAt = createdAt;
    	this.updatedAt = updatedAt;
    }
    
    
    public Long getId() {
        return this.id;
      }
    
    public String getName() {
        return this.name;
      }

    public String getClazz() {
        return this.clazz;
      }
    
    public Date getCreatedAt() {
        return this.createdAt;
      }
    
    public Date getUpdatedAt() {
        return this.updatedAt;
      }
    
    public void setId(long id) {
		this.id = id;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public void setClazz(String clazz) {
		this.clazz = clazz;
	}
    
    public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
    
    public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}