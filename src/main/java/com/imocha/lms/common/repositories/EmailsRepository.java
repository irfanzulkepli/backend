package com.imocha.lms.common.repositories;

import java.util.List;

import com.imocha.lms.common.entities.Emails;
import com.imocha.lms.common.enumerator.ContextableTypes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailsRepository extends JpaRepository<Emails, Long> {

    public List<Emails> findByContextableTypeAndContextableId(ContextableTypes contextableType,
            Long contextableId);
}
