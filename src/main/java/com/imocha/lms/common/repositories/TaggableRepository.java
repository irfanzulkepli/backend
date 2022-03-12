package com.imocha.lms.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.common.entities.Taggables;
import com.imocha.lms.common.entities.Tags;

public interface TaggableRepository extends JpaRepository<Taggables, Long> {

	public List<Taggables> findByTaggableTypeIgnoreCaseContainingAndTaggableId(String taggableType, Long taggableId);

	public Taggables findByTaggableTypeIgnoreCaseContainingAndTaggableIdAndTags(String taggableType, Long taggableId,
			Tags tag);
}
