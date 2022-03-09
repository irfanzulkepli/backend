package com.imocha.lms.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.entities.Taggables;

public interface TaggableRepository extends JpaRepository<Taggables, Long> {

	public List<Taggables> findByTaggableTypeIgnoreCaseContainingAndTaggableId(String taggableType, Long taggableId);
}
