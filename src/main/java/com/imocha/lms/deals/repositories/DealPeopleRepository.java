package com.imocha.lms.deals.repositories;

import java.util.Optional;

import com.imocha.lms.deals.entities.DealPeople;
import com.imocha.lms.deals.entities.Deals;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DealPeopleRepository extends JpaRepository<DealPeople, Long> {

    Optional<DealPeople> findByDeals(Deals deals);

}
