package com.imocha.lms.users.repository;

import com.imocha.lms.users.entities.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByKeycloakId(String keycloakId);
}
