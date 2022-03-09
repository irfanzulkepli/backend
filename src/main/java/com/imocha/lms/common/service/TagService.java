package com.imocha.lms.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.common.model.TagResponse;
import com.imocha.lms.common.repositories.TagRepository;
import com.imocha.lms.common.repositories.TaggableRepository;
import com.imocha.lms.entities.Tags;

@Service
public class TagService {

	@Autowired
	private TaggableRepository taggableRepository;

	@Autowired
	private TagRepository tagRepository;

	public List<TagResponse> getPersonTagById(Long id) {
		return this.taggableRepository.findByTaggableTypeIgnoreCaseContainingAndTaggableId("person", id).stream()
				.map(taggable -> {
					Tags tag = tagRepository.getById(taggable.getTagsId());
					TagResponse tagResponse = new TagResponse();
					BeanUtils.copyProperties(tag, tagResponse);
					return tagResponse;
				}).collect(Collectors.toList());
	}
}
