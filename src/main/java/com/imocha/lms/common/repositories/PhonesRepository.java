package com.imocha.lms.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.common.entities.Phones;

public interface PhonesRepository extends JpaRepository<Phones, Long> {

    public List<Phones> findByContextableTypeIgnoreCaseContainingAndContextableId(String contextableType,
            Long contextableId);
}
