package com.imocha.lms.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.entities.Activities;

public interface ActivitiesRepository extends JpaRepository<Activities, Long> {

	List<Activities> findByContextableTypeIgnoreCaseContainingAndContextableId(String contextableType,
			Long contextableId);
}
