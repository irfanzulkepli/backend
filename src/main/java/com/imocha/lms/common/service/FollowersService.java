package com.imocha.lms.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imocha.lms.common.entities.Followers;
import com.imocha.lms.common.repositories.FollowersRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FollowersService {

	@Autowired
	private FollowersRepository followersRepository;

	public Page<Followers> getFollowersByLeadsId(Long id, String leadType, Pageable pageable) {
		Page<Followers> followers = followersRepository
				.findByContextableTypeIgnoreCaseContainingAndContextableId(leadType, id, pageable);

		return followers;
	}

	public List<Followers> getFollowersByLeadsId(Long id, String leadType) {
		return followersRepository.findByContextableTypeIgnoreCaseContainingAndContextableId(leadType, id);
	}

	public Followers save(Followers follower) {
		return followersRepository.save(follower);
	}

	public void delete(Followers follower) {
		followersRepository.delete(follower);
	}

	public void deleteById(Long id) {
		followersRepository.deleteById(id);
	}
}
