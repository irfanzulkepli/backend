package com.imocha.lms.activities.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.activities.entities.Activities;

public interface ActivitiesRepository extends JpaRepository<Activities, Long> {

	List<Activities> findByContextableTypeIgnoreCaseContainingAndContextableId(String contextableType,
			Long contextableId);
}
