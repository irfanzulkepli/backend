package com.imocha.lms.common.repositories;

import java.util.List;
import java.util.Optional;

import com.imocha.lms.common.entities.Discussions;
import com.imocha.lms.common.enumerator.ContextableTypes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionsRepository extends JpaRepository<Discussions, Long> {

    Page<Discussions> findByUsers(Long id, Pageable pageable);

    List<Discussions> findByCommentableTypeAndCommentableId(ContextableTypes commentableType,
            Long commentableId);
}
