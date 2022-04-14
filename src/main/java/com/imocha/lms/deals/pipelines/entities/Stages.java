// Generated with g9.

package com.imocha.lms.deals.pipelines.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.common.audit.Auditable;

import lombok.Data;

@Data
@Entity(name = "stages")
public class Stages extends Auditable<String> {

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "pipeline_id", nullable = false)
    private Pipelines pipelines;
    @Column(name = "active")
    private boolean active = true;
}
