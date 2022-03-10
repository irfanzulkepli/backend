// Generated with g9.

package com.imocha.lms.stages.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.lms.pipelines.entities.Pipelines;
import com.imocha.lms.users.entities.Users;

import lombok.Data;

@Data
@Entity(name = "stages")
public class Stages implements Serializable {

    /** Primary key. */
    protected static final String PK = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 20)
    private long id;
    @Column(nullable = false, length = 191)
    private String name;
    @Column(precision = 10)
    private int probability;
    @Column(nullable = false, precision = 10)
    private int priority;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users users;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pipeline_id", nullable = false)
    private Pipelines pipelines;
}
