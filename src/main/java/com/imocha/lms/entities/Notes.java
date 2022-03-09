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

import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
@Entity
@Table(name = "notes", indexes = {
        @Index(name = "notesNotesNoteableTypeNoteableIdIndex", columnList = "noteable_type,noteable_id") })
public class Notes implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 20)
    private long id;
    @Column(nullable = false)
    private String note;
    @Column(name = "noteable_type", nullable = false, length = 191)
    private String noteableType;
    @Column(name = "noteable_id", nullable = false, precision = 20)
    private long noteableId;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Statuses statuses;

}
