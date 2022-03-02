// Generated with g9.

package com.imocha.lms.deals.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.lms.entities.Stages;

import lombok.Data;

@Data
@Entity(name = "deal_stage")
@IdClass(DealStage.DealStageId.class)
public class DealStage implements Serializable {

    /**
     * IdClass for primary key when using JPA annotations
     */
    public class DealStageId implements Serializable {
        Deals deals;
        Stages stages;
    }

    /** Primary key. */
    protected static final String PK = "DealStagePrimary";

    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne(optional = false)
    @Id
    @JoinColumn(name = "deal_id", nullable = false)
    private Deals deals;
    @ManyToOne(optional = false)
    @Id
    @JoinColumn(name = "stage_id", nullable = false)
    private Stages stages;
}
