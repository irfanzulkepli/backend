package com.imocha.lms.common.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.imocha.lms.common.entities.Discussions;
import com.imocha.lms.common.enumerator.ContextableTypes;
import com.imocha.lms.common.model.DiscussionsResponse;
import com.imocha.lms.common.repositories.DiscussionsRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DiscussionsService {
    @Autowired
	private DiscussionsRepository discussionsRepository;

	public List<DiscussionsResponse> getCommentsByDealsId(Long id) {
		return discussionsRepository.findByCommentableTypeAndCommentableId(ContextableTypes.DEAL, id).stream()
        .map(discussion -> {
            DiscussionsResponse discussionsResponse = new DiscussionsResponse();
            BeanUtils.copyProperties(discussion, discussionsResponse);
            return discussionsResponse;
        }).collect(Collectors.toList());
	}

    public Discussions get(long id) {
		Optional<Discussions> discussionOptional = discussionsRepository.findById(id);
		discussionOptional.orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));

		return discussionOptional.get();
	}

	public Discussions save(Discussions discussion) {
		return discussionsRepository.save(discussion);
	}

	public long delete(long id) {
        this.get(id);
		discussionsRepository.deleteById(id);

        return id;
	}
}
