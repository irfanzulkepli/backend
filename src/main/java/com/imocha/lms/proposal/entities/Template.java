package com.imocha.lms.proposal.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.imocha.common.audit.Auditable;

@Entity
@Table(name="template")
public class Template extends Auditable<String> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
	@NotNull
	private int id;
	
	@Column(columnDefinition = "VARCHAR(255)")
	@NotNull
	private String title;
	
	@Column(columnDefinition = "TEXT")
	@NotNull
	private String content;
	
	@Column(columnDefinition = "VARCHAR(255)")
	@NotNull
	private String status;
	
//	@Column(columnDefinition = "VARCHAR(255)")
//	private String created_by;
//	
//	@Column(columnDefinition = "VARCHAR(255)")
//	private String updated_by;
//	
//	@Column(columnDefinition = "DATETIME")
//	private Date created_at;
//	
//	@Column(columnDefinition = "DATETIME")
//	private Date updated_at;
	
	public Template(){
		
	}

	public Template(@NotNull String title, @NotNull String content, @NotNull String status) {
		super();
		this.title = title;
		this.content = content;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Template [id=" + id + ", title=" + title + ", content=" + content + ", status=" + status + "]";
	}

//	public String getCreated_by() {
//		return created_by;
//	}
//
//	public void setCreated_by(String created_by) {
//		this.created_by = created_by;
//	}
//
//	public String getUpdated_by() {
//		return updated_by;
//	}
//
//	public void setUpdated_by(String updated_by) {
//		this.updated_by = updated_by;
//	}
//
//	public Date getCreated_at() {
//		return created_at;
//	}
//
//	public void setCreated_at(Date created_at) {
//		this.created_at = created_at;
//	}
//
//	public Date getUpdated_at() {
//		return updated_at;
//	}
//
//	public void setUpdated_at(Date updated_at) {
//		this.updated_at = updated_at;
//	}
//
//	@Override
//	public String toString() {
//		return "Template [id=" + id + ", title=" + title + ", content=" + content + ", status=" + status
//				+ ", created_by=" + created_by + ", updated_by=" + updated_by + ", created_at=" + created_at
//				+ ", updated_at=" + updated_at + "]";
//	}
//	
	

	
	
	

}
