package com.imocha.lms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ziuser")
public class ZIUser implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
	private String username;
	private String password;
	private String roles;
	private String pNum;
	
	ZIUser(){
		
	}

	public ZIUser(long id, String username, String password, String roles, String pNum) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.pNum = pNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	@Override
	public String toString() {
		return "ZIUser [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + ", pNum="
				+ pNum + "]";
	}
	
}
