package com.imocha.lms.activities.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.activities.entities.Activities;
import com.imocha.lms.activities.entities.ActivityCollaborator;

public interface ActivitiesCollaboratorRepository extends JpaRepository<ActivityCollaborator, Long> {

	List<ActivityCollaborator> findByActivities(Activities activity);
}
