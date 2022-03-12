package com.imocha.lms.common.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.common.entities.Followers;

public interface FollowersRepository extends JpaRepository<Followers, Long> {

	Page<Followers> findByPeopleId(Long id, Pageable pageable);

	Page<Followers> findByContextableTypeIgnoreCaseContainingAndContextableId(String contextableType,
			Long contextableId, Pageable pageable);

	List<Followers> findByContextableTypeIgnoreCaseContainingAndContextableId(String contextableType,
			Long contextableId);
}
