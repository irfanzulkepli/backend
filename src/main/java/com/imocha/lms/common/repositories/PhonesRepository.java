package com.imocha.lms.common.repositories;

import java.util.List;

import com.imocha.lms.common.entities.Phones;
import com.imocha.lms.common.enumerator.ContextableTypes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhonesRepository extends JpaRepository<Phones, Long> {

    public List<Phones> findByContextableTypeAndContextableId(ContextableTypes contextableType,
            Long contextableId);
}
