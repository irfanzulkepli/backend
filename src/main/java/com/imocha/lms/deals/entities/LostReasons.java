// Generated with g9.

package com.imocha.lms.deals.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.imocha.common.audit.Auditable;

import lombok.Data;

@Data
@Entity(name = "lost_reasons")
public class LostReasons extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 20)
    private long id;
    @Column(name = "lost_reason", nullable = false, length = 191)
    private String lostReason;
    @Column(name = "active")
    private boolean active = true;
}
