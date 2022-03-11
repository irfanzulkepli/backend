package com.imocha.lms.common.service;

import com.imocha.lms.common.entities.Followers;
import com.imocha.lms.common.repositories.FollowersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FollowersService {

	@Autowired
	private FollowersRepository followersRepository;

	public Page<Followers> getFollowersByPersonId(Long id, Pageable pageable) {
		Page<Followers> followers = followersRepository
				.findByContextableTypeIgnoreCaseContainingAndContextableId("person", id, pageable);

		return followers;
	}
}
