package com.imocha.lms.activities.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.activities.entities.Activities;
import com.imocha.lms.activities.entities.ActivityParticipant;

public interface ActivitiesParticipantRepository extends JpaRepository<ActivityParticipant, Long> {

	List<ActivityParticipant> findByActivities(Activities activity);
}
