package com.imocha.lms.deals.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.imocha.common.audit.Auditable;
import com.imocha.lms.leads.entities.People;

import lombok.Data;

@Data
@Entity(name = "deal_people")
@IdClass(DealPeople.DealPeopleId.class)
public class DealPeople extends Auditable<String> {

    /**
     * IdClass for primary key when using JPA annotations
     */
    public static class DealPeopleId implements Serializable {
        Long deals;
        Long people;
    }

    @ManyToOne(optional = false)
    @Id
    @JoinColumn(name = "deal_id", nullable = false)
    private Deals deals;
    @ManyToOne(optional = false)
    @Id
    @JoinColumn(name = "person_id", nullable = false)
    private People people;

}
