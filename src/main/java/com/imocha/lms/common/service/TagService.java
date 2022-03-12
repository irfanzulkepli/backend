package com.imocha.lms.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.common.entities.Taggables;
import com.imocha.lms.common.entities.Tags;
import com.imocha.lms.common.model.TagResponse;
import com.imocha.lms.common.repositories.TagRepository;
import com.imocha.lms.common.repositories.TaggableRepository;

@Service
public class TagService {

	@Autowired
	private TaggableRepository taggableRepository;

	@Autowired
	private TagRepository tagRepository;

	public List<TagResponse> list() {
		List<Tags> tags = tagRepository.findAll();
		List<TagResponse> tagResponses = tags.stream().map(tag -> {
			TagResponse tagResponse = new TagResponse();
			BeanUtils.copyProperties(tag, tagResponse);

			return tagResponse;
		}).collect(Collectors.toList());

		return tagResponses;
	}

	public List<TagResponse> getLeadsTagById(Long id, String leadType) {
		return this.taggableRepository.findByTaggableTypeIgnoreCaseContainingAndTaggableId(leadType, id).stream()
				.map(taggable -> {
					Tags tag = tagRepository.getById(taggable.getTagsId());
					TagResponse tagResponse = new TagResponse();
					BeanUtils.copyProperties(tag, tagResponse);
					return tagResponse;
				}).collect(Collectors.toList());
	}

	public Taggables getTaggable(Tags tag, Long taggableId, String contextableType) {
		return taggableRepository.findByTaggableTypeIgnoreCaseContainingAndTaggableIdAndTags(contextableType,
				taggableId, tag);
	}

	public Tags getByTagId(Long id) {
		return tagRepository.getById(id);
	}

	public TagResponse save(Taggables taggables) {
		taggableRepository.save(taggables);
		Tags tag = tagRepository.getById(taggables.getTagsId());

		TagResponse tagResponse = new TagResponse();
		BeanUtils.copyProperties(tag, tagResponse);
		return tagResponse;
	}

	public void deleteByEntity(Taggables taggables) {
		taggableRepository.delete(taggables);
	}
}
