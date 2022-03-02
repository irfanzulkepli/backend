package com.imocha.lms.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.common.entities.Emails;

public interface EmailsRepository extends JpaRepository<Emails, Long> {

    public List<Emails> findByContextableTypeIgnoreCaseContainingAndContextableId(String contextableType,
            Long contextableId);
}
