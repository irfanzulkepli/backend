package com.imocha.lms.activities.repositories;

import java.util.List;

import com.imocha.lms.activities.entities.Activities;
import com.imocha.lms.common.enumerator.ContextableTypes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitiesRepository extends JpaRepository<Activities, Long> {

	List<Activities> findByContextableTypeAndContextableId(ContextableTypes contextableType,
			Long contextableId);
}
