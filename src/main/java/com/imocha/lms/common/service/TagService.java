package com.imocha.lms.common.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imocha.lms.common.entities.Taggables;
import com.imocha.lms.common.entities.Tags;
import com.imocha.lms.common.enumerator.ContextableTypes;
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

	public List<Taggables> getTaggablesByTagsAndTaggableType(long tagId, ContextableTypes contextableTypes) {
		Tags tag = tagRepository.findById(tagId).get();
		return taggableRepository.findByTagsAndTaggableType(tag, contextableTypes);
	}

	public Set<Long> getTaggableDealIdsByTagIdssAndTaggableType(Set<Long> tagIds) {
		Set<Long> taggableDealIds = new HashSet<Long>();

		for (Long tagId : tagIds) {
			List<Taggables> taggableList = this.getTaggablesByTagsAndTaggableType(tagId, ContextableTypes.DEAL);
			for (Taggables taggable : taggableList) {
				taggableDealIds.add(taggable.getTaggableId());
			}
		}

		return taggableDealIds;
	}

	public List<Taggables> getTaggableByTaggableIdAndTaggableType(Long id, ContextableTypes leadType) {
		return this.taggableRepository.findByTaggableTypeAndTaggableId(leadType, id);
	}

	public List<TagResponse> getLeadsTagById(Long id, ContextableTypes leadType) {
		return this.taggableRepository.findByTaggableTypeAndTaggableId(leadType, id).stream().map(taggable -> {
			Tags tag = tagRepository.getById(taggable.getTags().getId());
			TagResponse tagResponse = new TagResponse();
			BeanUtils.copyProperties(tag, tagResponse);
			return tagResponse;
		}).collect(Collectors.toList());
	}

	public Taggables getTaggable(Tags tag, Long taggableId, ContextableTypes contextableType) {
		return taggableRepository.findByTaggableTypeAndTaggableIdAndTags(contextableType, taggableId, tag);
	}

	public Tags getByTagId(Long id) {
		return tagRepository.getById(id);
	}

	public TagResponse save(Taggables taggables) {
		taggableRepository.save(taggables);
		Tags tag = tagRepository.getById(taggables.getTags().getId());

		TagResponse tagResponse = new TagResponse();
		BeanUtils.copyProperties(tag, tagResponse);
		return tagResponse;
	}

	public void deleteByEntity(Taggables taggables) {
		taggableRepository.delete(taggables);
	}
}
