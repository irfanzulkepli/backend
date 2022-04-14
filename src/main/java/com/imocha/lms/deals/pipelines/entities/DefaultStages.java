// Generated with g9.

package com.imocha.lms.deals.pipelines.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.imocha.common.audit.Auditable;

import lombok.Data;

@Data
@Entity(name = "default_stages")
public class DefaultStages extends Auditable<String> {

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

}
