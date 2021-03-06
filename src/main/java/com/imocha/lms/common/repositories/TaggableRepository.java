package com.imocha.lms.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.common.entities.Taggables;
import com.imocha.lms.common.entities.Tags;
import com.imocha.lms.common.enumerator.ContextableTypes;

public interface TaggableRepository extends JpaRepository<Taggables, Long> {

	public List<Taggables> findByTaggableTypeAndTaggableId(ContextableTypes taggableType, Long taggableId);

	public Taggables findByTaggableTypeAndTaggableIdAndTags(ContextableTypes taggableType, Long taggableId,
			Tags tag);
	
	public List<Taggables> findByTagsAndTaggableType(Tags tag, ContextableTypes contextableType);
}
