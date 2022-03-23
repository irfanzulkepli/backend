// Generated with g9.

package com.imocha.lms.common.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.users.entities.Users;

import org.mapstruct.EnumMapping;

import lombok.Data;

@Data
@Entity
@Table(name = "discussions", indexes = {
        @Index(name = "discussionsDiscussionsCommentableTypeCommentableIdIndex", columnList = "commentable_type,commentable_id") })
public class Discussions implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 20)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "commentable_type", length = 191)
    private ContextableTypes commentableType;

    @Column(name = "commentable_id", precision = 20)
    private long commentableId;
    @Column(name = "comment_body", nullable = false)
    private String commentBody;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "commented_by")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "comment_id_of_reply")
    private Discussions discussions2;
}
