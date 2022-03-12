package com.imocha.lms.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.common.entities.Tags;

public interface TagRepository extends JpaRepository<Tags, Long> {

}
