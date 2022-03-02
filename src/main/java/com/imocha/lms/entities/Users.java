// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users", indexes = { @Index(name = "users_email_IX", columnList = "email", unique = true) })
public class Users implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 20)
    private long id;
    @Column(name = "first_name", length = 191)
    private String firstName;
    @Column(name = "last_name", length = 191)
    private String lastName;
    @Column(unique = true, nullable = false, length = 160)
    private String email;
    @Column(length = 191)
    private String password;
    @Column(name = "last_login_at")
    private Date lastLoginAt;
    @Column(name = "invitation_token", length = 191)
    private String invitationToken;
    @Column(name = "remember_token", length = 100)
    private String rememberToken;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users users;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private Statuses statuses;
}
