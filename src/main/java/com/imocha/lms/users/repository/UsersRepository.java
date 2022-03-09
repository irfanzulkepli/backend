package com.imocha.lms.users.repository;

import com.imocha.lms.users.entities.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
