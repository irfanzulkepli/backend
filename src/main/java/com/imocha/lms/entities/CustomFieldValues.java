// Generated with g9.

package com.imocha.lms.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
@Entity(name = "custom_field_values")
public class CustomFieldValues implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    /**
     * The optimistic lock. Available via standard bean get/set operations.
     */
    @Version
    @Column(name = "LOCK_FLAG")
    private Integer lockFlag;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 20)
    private long id;
    @Column(nullable = false)
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "contextable_type", nullable = false, length = 191)
    private ContextableTypes contextableType;
    @Column(name = "contextable_id", nullable = false, length = 191)
    private String contextableId;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne(optional = false)
    @JoinColumn(name = "custom_field_id", nullable = false)
    private CustomFields customFields;
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Users users;
}
