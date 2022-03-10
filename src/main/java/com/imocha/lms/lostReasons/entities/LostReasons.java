// Generated with g9.

package com.imocha.lms.lostReasons.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
@Entity(name = "lost_reasons")
public class LostReasons implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 20)
    private long id;
    @Column(name = "lost_reason", nullable = false, length = 191)
    private String lostReason;
	@Column(name = "active")
	private boolean active = true;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users users;
}
