package com.imocha.lms.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imocha.lms.common.entities.Countries;

public interface CountriesRepository extends JpaRepository<Countries, Long> {

}
