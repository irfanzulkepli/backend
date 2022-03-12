package com.imocha.lms.activities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.activities.entities.ActivityTypes;

public interface ActivityTypesRepository extends JpaRepository<ActivityTypes, Long> {

}
