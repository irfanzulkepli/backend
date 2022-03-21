package com.imocha.lms.common.repositories;

import java.util.List;

import com.imocha.lms.common.entities.Followers;
import com.imocha.lms.common.enumerator.ContextableTypes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowersRepository extends JpaRepository<Followers, Long> {

	Page<Followers> findByPeopleId(Long id, Pageable pageable);

	Page<Followers> findByContextableTypeAndContextableId(ContextableTypes contextableType,
			Long contextableId, Pageable pageable);

	List<Followers> findByContextableTypeAndContextableId(ContextableTypes contextableType,
			Long contextableId);
}
